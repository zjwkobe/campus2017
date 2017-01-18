import React, {Component} from 'react';
import Touchable from '$yo-component/touchable';
import Scroller from '$yo-component/scroller';
import Header from '$component/header/index.js';
import yoHistory from '$common/history';
import Detail from '../detail/index.js';
import Info from '../info/index.js';
import Price from '../price/index.js';
import './index.scss';

// 测试数据
const priceItem = {
    initialPrice: {
        title: '精致家庭房',
        type: 'initial',
        remark: '有窗',
        expanded: false,
        price: 122,
        additionalTags: ['group', 'night', 'return']
    },
    comparePrice: [
        {
            title: '酒店直销',
            type: 'compare',
            price: 135,
            remark: '(限时抢购，节省33元)-优惠价',
            payMethod: '到店付',
            additionalTags: ['return'],
            priceRemark: [
                {
                    amount: 135,
                    type: 'RMB'
                }, {
                    amount: 135,
                    type: 'ticket'
                }
            ]
        }, {
            title: '酒店直销',
            type: 'compare',
            price: 122,
            tags: ['cheapest'],
            recommended: true,
            remark: '(今日特价)-优惠价',
            payMethod: '到店付',
            priceRemark: [
                {
                    amount: 135,
                    type: 'RMB'
                }, {
                    amount: 13,
                    type: 'RMB'
                }
            ]
        }, {
            title: '酒店夜销',
            type: 'compare',
            price: 168,
            tags: ['cheapest'],
            recommended: true,
            payMethod: '在线付',
            additionalTags: ['most-reliable', 'fast-confirm']
        }, {
            title: '五星旅游网',
            type: 'compare',
            remark: '(今日特价)-优惠价',
            price: 129,
            tags: ['cheapest'],
            recommended: true,
            payMethod: '到店付',
            priceRemark: [
                {
                    amount: 135,
                    type: 'RMB'
                }, {
                    amount: 6,
                    type: 'RMB'
                }
            ]
        }, {
            title: '爱游网',
            type: 'compare',
            remark: '(今日特价)-优惠价',
            price: 124,
            tags: ['cheapest'],
            recommended: true,
            payMethod: '到店付',
            priceRemark: [
                {
                    amount: 135,
                    type: 'RMB'
                }, {
                    amount: 11,
                    type: 'RMB'
                }
            ]
        }
    ]
}

const priceSet = Array(4).fill(priceItem) // 生成测试数据

class HomePage extends Component {
    render() {
        return (
            <div className="yo-flex">
                <Scroller extraClass="flex">
                    <div className="body">
                        <Header
                            title="北京有怡公寓"
                            left={{
                            title: '&#xf07d',
                            onTap: () => {} // 可扩展点击跳转
                        }}
                            right={{
                            title: '<img src="../image/icon/logo.jpg" />'
                        }}/>

                        <Detail
                            imgCount={9}
                            average={3.9}
                            positiveComments={65}
                            negativeComments={9}
                            address="北京市朝阳区西大望路甲12号北"
                            around="[商圈]大望路区域"
                            openTime="2014年开业 4008909123"
                            facilities={['../image/icon/wifi.jpg', '../image/icon/ring.jpg', '../image/icon/bag.jpg', '../image/icon/shower.jpg', '../image/icon/cloth.jpg']}/>

                        <Info checkInDate="02月27日" checkOutDate="02月28日" dayCount="1"></Info>

                        <Price priceSet={priceSet}></Price>
                    </div>
                </Scroller>
            </div>
        )
    }
}

export default HomePage;