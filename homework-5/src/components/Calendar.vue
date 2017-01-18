<template>
    <div class="calendar-container">
        <div class="scroll-btn left-btn"></div>
        <ul class="date-list">
            <li>
                <span>02-19去</span>
                <span>03-04回</span>
                <span class="RMB">3440</span>
            </li>
            <li>
                <span>02-19去</span>
                <span>03-04回</span>
                <span class="RMB">3440</span>
            </li>
            <li>
                <span>02-19去</span>
                <span>03-04回</span>
                <span class="RMB">3440</span>
            </li>
            <li>
                <span>02-19去</span>
                <span>03-04回</span>
                <span class="RMB">3440</span>
            </li>
            <li class="current">
                <span>02-19去</span>
                <span>03-04回</span>
                <span class="RMB">3440</span>
            </li>
        </ul>
        <div class="scroll-btn right-btn"></div>
        <div class="calendar-sign"></div>
    </div>
</template>

<script>
    export default {
        name: 'calendar'
    }
</script>

<style lang="less">
    .calendar-container {
        overflow: hidden;
        box-sizing: border-box;
        margin-bottom: 19px;
    }
    .scroll-btn {
        position: absolute;
        top: 0;
        width: 47px;
        height: 73px;
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        border-bottom: 1px solid #268fc5;
        cursor: pointer;
    }
    .left-btn {
        left: 0;
        background-image: url(../assets/images/left.png);
    }
    .right-btn {
        right: 96px;
        background-image: url(../assets/images/right.png);
    }
    .calendar-sign {
        position: absolute;
        right: 0;
        top: 0;
        width: 96px;
        height: 74px;
        background-image: url(../assets/images/calendar-sign.png);
        background-size: contain;
        background-position: center;
    }
    .date-list {
        margin-left: 47px;
        margin-right: 143px;
        height: 73px;
        display: -webkit-flex;
        display: flex;
        border-bottom: 1px solid #268fc5;
        .current {
            position: relative;
            border-bottom: 1px solid #fff;
            border-top: 1px solid #268fc5;
            span {
                color: #268fc5;
                font-weight: bold;
            }
            &:after {
                content: '';
                position: absolute;
                top: 1px;
                left: 1px;
                height: 14px;
                width: 14px;
                background-image: url('../assets/images/low.png');
                background-size: contain;
            }
        }
        li {
            width: 103px;
            height: 74px;
            border-left: 1px solid #7dbcdc;
            box-sizing: border-box;
            padding-top: 8px;
            cursor: pointer;
            &:hover {
                position: relative;
                border-bottom: 1px solid #fff;
                border-top: 1px solid #268fc5;
                span {
                    color: #268fc5;
                    font-weight: bold;
                }
            }
            &:last-child {
                border-right: 1px solid #7dbcdc;
            }
            span {
                display: block;
                line-height: 20px;
                text-align: center;
                font-size: 12px;
                &:last-child {
                    color: #268fc5;
                    font-weight: bold;
                }
            }
        }
    }
</style>