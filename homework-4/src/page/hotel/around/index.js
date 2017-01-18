import React, {Component} from 'react';
import Touchable from '$yo-component/touchable';
import './index.scss';

class Around extends Component {
    render() {
        return (
            <div className="detail around-hotel">
                <ul className="detail-list">
                    <Touchable touchClass="touchable-highlight">
                        <li className="single-line">
                            <p>
                                附近热销酒店<span className="count">({this.props.hotCount}家)</span>
                            </p>
                            <span className="right-content">
                                <span className="yo-ico">&#xf07f;</span>
                            </span>
                        </li>
                    </Touchable>
                    <Touchable touchClass="touchable-highlight">
                        <li className="single-line">
                            <p>
                                同类低价酒店<span className="count">({this.props.lowPriceCount}家)</span>
                            </p>
                            <span className="right-content">
                                <span className="yo-ico">&#xf07f;</span>
                            </span>
                        </li>
                    </Touchable>
                </ul>
                <div className="remark">
                    注：房型信息以代理商、酒店及订单填写页实际内容为准，去哪儿不保证所有信息的准确性，仅供参考。
                </div>
                <ul className="footer yo-flex">
                    <li>机票</li>
                    <li>酒店</li>
                    <li>公寓</li>
                    <li>更多</li>
                </ul>
                <p className="icp">Qunar 京ICP备05021087
                    <a href="#">意见反馈</a>
                </p>
            </div>
        )
    }
}

export default Around;