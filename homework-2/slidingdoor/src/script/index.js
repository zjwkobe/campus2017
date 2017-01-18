function SlidingDoor(config) {
    /**
     * 滑动门构造函数
     * @constructor
     * @param {Object} config.mountElemnt 挂载元素
     * @param {Array} config.imgArray 包含图片URL的数组
     * @param {Number} config.containerWidth 容器宽度
     * @param {Number} config.imgWidth 图片宽度
     * @param {Number} [config.imgHeight = auto] 图片高度， 默认auto
     * @param {Number} [config.duration = 600] 动画时间，单位ms，默认600ms
     * @param {(String | Function)} [config.easingFunction = easeInOut] 缓动函数，若自定义缓动函数，传入的函数需要包含四个参数：t,b,c,d，t表示当前动画时间，b表示初始值，c表示变化量，d表示动画持续时间，返回值类型为Number，默认easeInOut
     * @param {Number} [config.initialImg = 0] 初始显示的图片索引，默认0
     */

    let {
        mountElement,
        imgArray,
        containerWidth,
        imgWidth,
        imgHeight = 'auto',
        duration = 600,
        easingFunction = 'easeInOut',
        initailImg = 0
    } = config

    // 参数检查
    if (!this.isDOM(mountElement)) {
        throw new Error('Argument "mountElement" must be a DOM element.')
    }

    if (!Array.isArray(imgArray)) {
        throw new Error('Argument "imgArray" must be a non-empty array of image urls.')
    }

    this.isNonNegativeNumber([{
        value: containerWidth,
        argumentName: 'containerWidth'
    }, {
        value: imgWidth,
        argumentName: 'imgWidth'
    }])

    let imgCount = imgArray.length
    let collapseWidth = (containerWidth - imgWidth) / (imgCount - 1)
    let imgElements = []
    let containerElement = document.createElement('div')
    let current = imgCount - 1

    // 若初始图片索引超出范围或未指定则重置为0
    if (typeof initailImg === 'number') {
        if (initailImg < 0 || initailImg >= imgCount) {
            initailImg = 0
        }
    } else {
        initailImg = 0
    }

    // 若动画时间超出范围或未指定则重置为600
    if (typeof duration === 'number') {
        if (duration < 0) {
            duration = 600
        }
    } else {
        duration = 600
    }

    // 若提供的缓动函数不为函数或指定的缓动函数不存在则重置为二次方的easeInOut
    if (typeof easingFunction !== 'function' && typeof SlidingItem.prototype.easingFunction[easingFunction] !== 'function') {
        easingFunction = SlidingItem.prototype.easingFunction['easeInOut']
    } else if (typeof easingFunction === 'string') {
        easingFunction = SlidingItem.prototype.easingFunction[easingFunction]
    }

    if (typeof imgHeight !== 'number') {
        imgHeight = 'auto'
    }

    imgArray.forEach((url, i) => {
        let img
        let config = {
            url: url,
            isExpand: false,
            duration: duration,
            easingFunction: easingFunction,
            collapseWidth: collapseWidth,
            imgWidth: imgWidth,
            imgHeight: imgHeight
        }

        if (i === initailImg) {
            config.isExpand = true
        }

        img = new SlidingItem(config)

        // 添加监听器
        img.addEventListener('mouseenter', () => {
            let index = this.nodeIndex(img)

            if (index === current) return

            imgElements.forEach(img => {
                img.collapse()
            })
            imgElements[index].expand()
            current = index
        })

        imgElements.push(img)
        containerElement.appendChild(img)
    })

    // 容器样式设置
    containerElement.style.width = containerWidth + 5 + 'px'
    containerElement.style.overflow = 'hidden'

    // 挂载到目标元素
    mountElement.appendChild(containerElement)
}

// 工具函数：检测是否DOM
SlidingDoor.prototype.isDOM = function(obj) {
    if (obj && obj.nodeType !== 1) {
        return false
    } else {
        try {
            obj.cloneNode(true)
            return true
        } catch (e) {
            return false
        }
    }
}

// 工具函数：检测是否非负数，否则报错
SlidingDoor.prototype.isNonNegativeNumber = function(config) {
    config.forEach(option => {
        if (typeof option.value !== 'number') {
            throw new Error(`Argument "${option.argumentName}" must be a number.`)
        } else if (option.value < 0) {
            throw new Error(`Argument "${option.argumentName}" must be a positive number.`)
        }
    })
}

// 工具函数：获取元素的索引
SlidingDoor.prototype.nodeIndex = function(node) {
    let parentNode = node.parentNode
    let nodeName = node.nodeName
    let nodeList = parentNode.getElementsByTagName(nodeName)
    let index = 0

    ;
    [...nodeList].forEach((el, i) => {
        if (el === node) {
            index = i
            return false
        }
    })

    return index
}

function SlidingItem(config) {
    /**
     * @constructor
     * @param {String} config.url 图片的URL
     * @param {Boolean} config.isExpand 是否展开
     * @param {Number} config.duration 动画时间，单位ms
     * @param {Function} config.easingFunction 缓动函数
     * @param {Number} config.collapseWidth 折叠宽度
     * @param {Number} config.imgWidth 图片宽度
     * @param {Number} config.imgHeight 图片高度
     * @return {Object}
     */

    let {
        url,
        isExpand,
        duration,
        easingFunction,
        collapseWidth,
        imgWidth,
        imgHeight
    } = config

    let img = new Image()
    let imgContainer = document.createElement('div')
    let startTime
    let requestId
    let currentWidth
    let requestAnimationFrame = window.requestAnimationFrame || window.mozRequestAnimationFrame || window.webkitRequestAnimationFrame || window.msRequestAnimationFrame

    if (typeof imgHeight === 'number') {
        img.style.height = imgHeight + 'px'
    } else {
        img.style.height = 'auto'
    }

    if (isExpand) {
        currentWidth = imgWidth
    } else {
        currentWidth = collapseWidth
    }

    img.src = url
    img.style.width = imgWidth + 'px'
    imgContainer.style.float = 'left'
    imgContainer.appendChild(img)
    imgContainer.style.width = currentWidth + 'px'

    // 折叠函数
    function collapse() {
        if (!isExpand) return

        if (requestId > 0) {
            cancelAnimationFrame(requestId)
        }

        isExpand = false
        startTime = new Date().getTime()
        runCollapse()
    }

    // 折叠动画函数
    function runCollapse() {
        let t = new Date().getTime() - startTime
        let change = collapseWidth - currentWidth
        currentWidth = easingFunction(t, currentWidth, change, duration)

        if (t < duration) {
            requestId = requestAnimationFrame(runCollapse)
        } else {
            currentWidth = collapseWidth
            requestId = 0
        }

        imgContainer.style.width = currentWidth + 'px'
    }

    // 展开函数
    function expand() {
        if (isExpand) return

        if (requestId > 0) {
            cancelAnimationFrame(requestId)
        }

        isExpand = true
        startTime = new Date().getTime()
        runExpand()
    }

    // 展开动画函数
    function runExpand() {
        let t = new Date().getTime() - startTime
        let change = imgWidth - currentWidth
        currentWidth = easingFunction(t, currentWidth, change, duration)

        if (t < duration) {
            requestId = requestAnimationFrame(runExpand)
        } else {
            currentWidth = imgWidth
            requestId = 0
        }

        imgContainer.style.width = currentWidth + 'px'
    }

    // 暴露接口
    imgContainer.collapse = collapse
    imgContainer.expand = expand

    return imgContainer
}

// 内置缓动函数
SlidingItem.prototype.easingFunction = {
    linear: function(t, b, c, d) {
        return c * t / d + b
    },
    easeIn: function(t, b, c, d) {
        return c * (t /= d) * t + b
    },
    easeOut: function(t, b, c, d) {
        return -c * (t /= d) * (t - 2) + b
    },
    easeInOut: function(t, b, c, d) {
        if ((t /= d / 2) < 1) return c / 2 * t * t + b
        return -c / 2 * ((--t) * (t - 2) - 1) + b
    }
}

// 使用示例

// 图片URL数组
let imgArray = ['image/door1.png', 'image/door2.png', 'image/door3.png', 'image/door4.png']

// 构造配置
let config = {
    mountElement: document.querySelector('#sliding-door-container'), // 挂载元素
    imgArray: imgArray,
    containerWidth: 925, // 容器宽度
    imgWidth: 475, // 图片宽度
    imgHeight: 477, // 图片高度
    duration: 1000, // 动画持续时间
    easingFunction: 'easeInOut', // 缓动函数
    initailImg: 3 // 初始显示图片索引
}

// 新建实例
new SlidingDoor(config)