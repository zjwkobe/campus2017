import React, {Component} from 'react';
import Touchable from '$yo-component/touchable';
import HotelPrice from '$component/hotelprice';
import Around from '../around/index.js';
import './index.scss';

class Price extends Component {
    render() {
        return (
            <div>
                <div className="price-compare">
                    <div className="initial-price">
                        {this
                            .props
                            .priceSet
                            .map((price, i) => {
                                return (i === 0) // 若为第一个则在其后添加“更多报价”
                                    ? (
                                        <div key={i}>
                                            <HotelPrice
                                                initialPrice={price.initialPrice}
                                                comparePrice={price.comparePrice}
                                                key={i}></HotelPrice>
                                            <div className="more-price">更多报价...</div>
                                        </div>
                                    )
                                    : (
                                        <HotelPrice
                                            initialPrice={price.initialPrice}
                                            comparePrice={price.comparePrice}
                                            key={i}></HotelPrice>
                                    )
                            })}
                    </div>
                </div>
                <Around hotCount="85" lowPriceCount="45"></Around>
            </div>
        )
    }
}

export default Price;