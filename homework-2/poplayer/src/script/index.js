function PopLayer(popLayerDOM, closeDOM, maskClose) {
    /**
     * PopLayer构造函数，返回弹出层实例
     * @constructor
     * @param {Object} popLayerDOM 自定义弹出层DOM元素
     * @param {Object} closeDOM 自定义关闭按钮DOM元素
     * @param {Boolean} maskClose 是否点击蒙版关闭
     * @method {Function} show 显示弹出层
     * @method {Function} close 关闭弹出层
     */

    // 参数检查
    if (!PopLayer.prototype.isDOM(popLayerDOM) || !PopLayer.prototype.isDOM(closeDOM)) {
        throw new Error('Arguments "popLayerDOM" and "closeDOM" should be DOM elements, got ' + (typeof popLayerDOM) + ' and ' + (typeof closeDOM))
    }

    if (typeof maskClose !== 'boolean') {
        throw new Error('Argument "maskClose" should be a Boolean, got ' + typeof maskClose)
    }

    // 显示蒙版和弹出层
    function show() {
        this.append([{
            el: mask,
            target: body
        }, {
            el: popLayerDOM,
            target: body
        }])
    }

    // 关闭蒙版和弹出层
    function close() {
        remove(mask, popLayerDOM)
    }

    // 工具函数：批量移除元素
    function remove() {
        for (var i = 0, len = arguments.length; i < len; i++) {
            arguments[i].remove()
        }
    }

    // 初始化
    function init() {
        closeDOM.addEventListener('click', close)

        if (maskClose) {
            mask.addEventListener('click', close)
        }
    }

    var body = document.getElementsByTagName('body')[0]
    var mask = this.initElement('div', 'mask')

    // 暴露公共接口
    this.close = close
    this.show = show

    init()

    return this
}

// 工具函数：检测是否DOM
PopLayer.prototype.isDOM = function(obj) {
    if (obj.nodeType !== 1) {
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

// 工具函数：批量向指定元素添加子元素
PopLayer.prototype.append = function(config) {
    config.forEach(function(item) {
        item.target.appendChild(item.el)
    })
}

// 工具函数：生成含有指定class和指定tag的元素
PopLayer.prototype.initElement = function(tag, className) {
    var el = document.createElement(tag)
    el.classList.add(className)
    return el
}

// 使用示例
document.querySelector('#btn').addEventListener('click', function() {
    // 创建弹出层DOM元素
    var popLayerDOM = PopLayer.prototype.initElement('div', 'pop-layer-container')
    var closeDOM = PopLayer.prototype.initElement('div', 'close')
    var popLayer = PopLayer.prototype.initElement('div', 'pop-layer')

    PopLayer.prototype.append([{
        el: closeDOM,
        target: popLayerDOM
    }, {
        el: popLayer,
        target: popLayerDOM
    }])

    // 新建弹出层实例
    var ins = new PopLayer(popLayerDOM, closeDOM, true)

    // 显示弹出层
    ins.show()
})