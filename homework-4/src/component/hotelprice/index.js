import React, {Component} from 'react';
import Touchable from '$yo-component/touchable';
import PriceItem from '$component/priceitem'
import './index.scss';

class HotelPrice extends Component {
    constructor(props) {
        super(props)
        this.state = {
            hide: true
        }
    }
    _hide() {
        this.setState({
            hide: !this.state.hide
        })
    }
    render() {
        return (
            <div>
                <PriceItem
                    config={this.props.initialPrice}
                    hide={this
                    ._hide
                    .bind(this)}></PriceItem>
                <div
                    className={this.state.hide
                    ? "hidden compare-list"
                    : "compare-list"}>
                    {this
                        .props
                        .comparePrice
                        .map((config, i) => {
                            return <PriceItem config={config} key={i}></PriceItem>
                        })}
                </div>
            </div>
        )
    }
}

export default HotelPrice;