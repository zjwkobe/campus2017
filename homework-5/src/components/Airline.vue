<template>
    <div class="table">
        <ul class="table-head">
            <li>航班信息</li>
            <li>当地起飞时间</li>
            <li>旅行总时长<span class="sort-container"><span class="sort-btn asc"></span><span class="sort-btn desc"></span></span></li>
            <li>当地降落时间</li>
            <li>推荐网站</li>
            <li>最低报价<span class="sort-container"><span class="sort-btn asc"></span><span class="sort-btn desc"></span></span></li>
        </ul>
        <div class="table-body">
            <div class="table-item">
                <div class="table-airlines">
                    <div class="table-airline-item">
                        <div class="airline-title">
                            <p class="company-logo DL"><span>达美航空</span></p>
                            <p class="share-airline">DL128 330(宽)</p>
                            <p>DL4786 CRJ(小)</p>
                        </div>
                        <div class="fly-time start-time">
                            <p>01:20</p>
                            <p>首都机场 PEK T3</p>
                        </div>
                        <div class="travel-time">
                            <div class="estimated-time">约16小时</div>
                            <div class="travel-bar"></div>
                            <div class="location interchange"><span>旧金山SFO</span></div>
                            <div class="location stop"><span>洛杉矶机场</span></div>
                        </div>
                        <div class="fly-time end-time">
                            <p>17:15</p>
                            <p>迈克卡兰国际机场 LAS</p>
                        </div>
                    </div>
                    <div class="table-airline-item">
                        <div class="airline-title">
                            <p class="company-logo DL"><span>达美航空</span></p>
                            <p class="share-airline">DL128 330(宽)</p>
                            <p>DL4786 CRJ(小)</p>
                        </div>
                        <div class="fly-time start-time">
                            <p>01:20</p>
                            <p>首都机场 PEK T3</p>
                        </div>
                        <div class="travel-time">
                            <div class="estimated-time">约16小时</div>
                            <div class="travel-bar"></div>
                            <div class="location interchange"><span>旧金山SFO</span></div>
                            <div class="location stop"><span>洛杉矶机场</span></div>
                        </div>
                        <div class="fly-time end-time">
                            <p>17:15</p>
                            <p>迈克卡兰国际机场 LAS</p>
                        </div>
                    </div>
                </div>
                <div class="table-order-info">
                    <div class="recommended-site-container">
                        <ul class="recommended-site">
                            <li>
                                <span>达美航空</span>
                                <span class="RMB">4091</span>
                            </li><li>
                                <span>留学生票</span>
                                <span class="RMB">4091</span>
                            </li>
                        </ul>
                    </div>
                    <div class="lowest-offer-container">
                        <div class="lowest-offer">
                            <p class="RMB"><span>2259</span>票价</p>
                            <p class="tax RMB">2784税费</p>
                            <button class="order"></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Airline'
    }
</script>

<style lang="less">
    .table-body,
    .table-item {
        overflow: hidden;
        position: relative;
        background-color: #f3fbfd;
    }
    .table-head {
        position: relative;
        height: 35px;
        background: #26b4d3;
        margin-top: 5px;
        line-height: 35px;
        color: #fff;
        li {
            position: relative;
            float: left;
            text-align: center;
            font-size: 14px;
            &:nth-child(1) {
                width: 191px;
            }
            &:nth-child(2), &:nth-child(4) {
                width: 149px;
            }
            &:nth-child(3) {
                width: 150px;
            }
            &:nth-child(5) {
                width: 130px;
            }
            &:nth-child(6) {
                width: 143px;
            }
        }
        &:before {
            position: absolute;
            content: '';
            top: -5px;
            width: 100%;
            height: 4px;
            background: url(../assets/images/bar-bg.png) repeat-x;
        }
    }
    .sort-container {
        position: absolute;
        top: 50%;
        right: 24px;
        transform: translate(0, -50%);
        display: inline-block;
        width: 7px;
        height: 10px;
        .sort-btn {
            position: absolute;
            left: 0;
            display: inline-block;
            width: 7px;
            height: 4px;
            background-size: contain;
            background-position: center;
        }
        .asc {
            top: 0;
            background-image: url(../assets/images/top.png);
        }
        .desc {
            top: 6px;
            background-image: url(../assets/images/bottom.png);
        }
    }
    .table-airlines {
        float: left;
        width: 639px;
        overflow: hidden;
    }
    .table-airline-item {
        height: 95px;
        overflow: hidden;
        border-bottom: 1px dashed #dadddd;
        & > div {
            float: left;
            font-size: 12px;
            text-align: center;
            &:nth-child(1) {
                text-align: left;
                text-indent: 65px;
                width: 191px;
            }
            &:nth-child(2), &:nth-child(4) {
                width: 149px;
            }
            &:nth-child(3) {
                width: 150px;
            }
            &:nth-child(5) {
                width: 130px;
            }
            &:nth-child(6) {
                width: 143px;
            }
        }
    }
    .table-order-info {
        position: absolute;
        right: 0;
        top: 0;
        bottom: 0;
        width: 273px;
        font-size: 12px;
    }
    .airline-title {
        color: #666;
    }
    .company-logo {
        padding: 15px 0;
        line-height: 16px;
        color: #000;
        & > span {
            position: relative;
            &:before {
                position: absolute;
                bottom: -3px;
                left: -30px;
                content: '';
                width: 23px;
                height: 20px;
                background-position: center;
                background-size: contain;
            }
        }
    }
    .DL {
        &>span:before {
            background-image: url(../assets/images/DL.png);
        }
    }
    .share-airline {
        &:after {
            content: '共享';
            color: #25b4d2;
            padding-left: 4px;
        }
    }
    .fly-time {
        & > p {
            &:first-child {
                padding: 15px 0;
                font-size: 18px;
                font-family: Arial;
            }
        }
    }
    .estimated-time {
        padding-top: 15px;
        padding-bottom: 22px;
        color: #666;
    }
    .location {
        position: relative;
        color: #666;
        line-height: 16px;
        &>span {
            position: relative;
            &:before {
                position: absolute;
                left: -20px;
                top: 0;
                width: 14px;
                height: 14px;
                content: '';
                background-position: center;
                background-size: contain;
                background-repeat: no-repeat;
            }
        }
    }
    .interchange {
        &>span:before {
            background-image: url(../assets/images/interchange.png);
        }
    }
    .stop {
        &>span:before {
            background-image: url(../assets/images/stop.png);
        }
    }
    .recommended-site,
    .lowest-offer {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }
    .recommended-site-container,
    .lowest-offer-container {
        float: left;
        width: 50%;
        height: 100%;
        position: relative;
    }
    .recommended-site {
        li {
            padding-bottom: 10px;
            color: #0e83bf;
            & > span {
                line-height: 16px;
                display: block;
                &:last-child {
                    text-align: right;
                    color: #ff7200;
                }
            }
            &:last-child {
                padding-bottom: 0;
            }
        }
    }
    .lowest-offer {
        width: 100%;
        text-align: right;
        box-sizing: border-box;
        padding-right: 20px;
        & > p {
            &:first-child {
                &:before {
                    color: #0e83bf;
                    font-family: Arial;
                }
                & > span {
                    font-size: 26px;
                    color: #0e83bf;
                    font-family: Arial;
                    padding-right: 8px;
                }
            }
        }
    }
    .tax {
        color: #666;
        font-family: Arial;
    }
    .order {
        border: none;
        outline: none;
        width: 75px;
        height: 40px;
        background-image: url(../assets/images/book.png);
        background-size: contain;
        background-position: center;
        cursor: pointer;
        margin-top: 10px;
    }
</style>