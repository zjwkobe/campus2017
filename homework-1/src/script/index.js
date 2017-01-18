function IEhack() {
    var _QRcode = $('#QR-code')
    var _floatNav = $('#float-nav-container')

    function isIE(version) {
        var b = document.createElement('b')
        b.innerHTML = '<!--[if IE ' + version + ']><i></i><![endif]-->'
        return b.getElementsByTagName('i').length === 1
    }

    function floatElementsResize() {
        var clientWidth = document.documentElement.clientWidth

        if (clientWidth < 980) {
            _QRcode.hide()
            _floatNav.hide()
        } else {
            _QRcode.css('left', ((clientWidth - 980) / 2 - 194) + 'px')
            _floatNav.css('right', ((clientWidth - 980) / 2 - 238) + 'px')
        }
    }

    function floatElementsHandler() {
        $(window).on('resize', floatElementsResize)
    }

    function init() {
        if (isIE(8)) {
            $('.activity-normal-item-container:even')
                .addClass('activity-normal-item-container-odd')
            $('.location-list li:even')
                .addClass('location-list-item-odd')
            $('.location-list li:last .location-dot')
                .css('width', '42px')
            floatElementsHandler()
            floatElementsResize()
        }
    }

    init()
}

// 活动阶段指示
function stageStatus() {
    var stage1 = $('#stage-item-1')
    var stage2 = $('#stage-item-2')
    var stage3 = $('#stage-item-3')
    var stages = [stage1, stage2, stage3]
    var stageIndicator = $('#stage-indicator')
    var d = new Date()
    var year = d.getFullYear()
    var month = d.getMonth()
    var date = d.getDate()

    function setStage(count) {
        if (count === 0) {
            stageIndicator.hide()
        } else {
            stageIndicator.addClass('stage-indicator-' + count)
        }
        for (var i = 0; i < (count - 1); i++) {
            stages[i].addClass('stage-item-' + (i + 1) + '-overdue')
        }
    }

    if (year > 2014) {
        setStage(3)
    } else {
        if (month > 10) {
            setStage(3)
        } else {
            if (date === 30) {
                setStage(3)
            } else if (date >= 25) {
                setStage(2)
            } else if (date >= 10) {
                setStage(1)
            } else {
                setStage(0)
            }
        }
    }
}

// 在页面滚动超过600px之后再显示浮动导航栏
function floatVisibility() {
    var _QRcode = $('#QR-code')
    var _floatNav = $('#float-nav-container')

    function scrollHandler() {
        var top = $(document).scrollTop()
        var width = document.documentElement.clientWidth
        var QRLeft = (width - 980) / 2 - 194
        var floatRight = (width - 980) / 2 - 238

        if (top > 600 && width > 1429) {
            _QRcode.fadeIn(300)
            _QRcode.css('left', QRLeft)
            _floatNav.fadeIn(300)
            _floatNav.css('right', floatRight)
        } else {
            _QRcode.fadeOut(300)
            _floatNav.fadeOut(300)
        }
    }

    $(window).on('scroll', scrollHandler)
    $(window).on('resize', scrollHandler)
    scrollHandler()
}

// 地区
function Regions() {
    var _index = 0
    var _locations = $('#location-list li')

    function setActive(index) {
        _locations.removeClass('location-active')
        _locations.eq(index).addClass('location-active')
        _index = index
    }

    function locationHandler(e) {
        e.preventDefault()
        var index = $(this).index()
        setActive(index)
    }

    function tabKeyHandler(e) {
        e.preventDefault()
        if (e.which === 9) {
            if (_index > 9) {
                setActive(0)
            } else {
                setActive(_index + 1)
            }
        }
    }

    function init() {
        setActive(_index)
        _locations.click(locationHandler)
        $(window).on('keydown', tabKeyHandler)
    }

    init()
}

function onLoad() {
    new IEhack()
    Regions()
    stageStatus()
    floatVisibility()
}

$(document).ready(onLoad)