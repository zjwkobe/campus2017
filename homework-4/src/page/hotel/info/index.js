import React, {Component} from 'react';
import Touchable from '$yo-component/touchable';
import './index.scss';

class Info extends Component{
    render() {
        return (
            <div className="info">
                <Touchable touchClass="touchable-highlight">
                    <h3>
                        <img src="../../src/image/icon/calendar.jpg" />
                        <span>{this.props.checkInDate}入住 {this.props.checkOutDate}离店</span>
                        <span className="right-content">共{this.props.dayCount}晚<span className="yo-ico">&#xf07f;</span></span>
                    </h3>
                </Touchable>
            </div>
        )
    }
}

export default Info;