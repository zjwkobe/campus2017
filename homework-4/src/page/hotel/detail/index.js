import React, {Component} from 'react';
import Touchable from '$yo-component/touchable';
import './index.scss';

class Detail extends Component {
    render() {
        return (
            <div className="detail">
                <div className="detail-photo">
                    <div className="photo-count">共{this.props.imgCount}张</div>
                </div>
                <ul className="detail-list">
                    <Touchable touchClass="touchable-highlight">
                        <li className="single-line">
                            <p>
                            <span className="average">{this.props.average}</span>分 / 好评{this.props.positiveComments} 差评{this.props.negativeComments}
                            </p>
                            <span className="right-content">看点评<span className="yo-ico">&#xf07f;</span></span>
                        </li>
                    </Touchable>

                    <Touchable touchClass="touchable-highlight">
                        <li className="double-line">
                            <p>
                                {this.props.address}
                            </p>
                            <p className="around">
                                {this.props.around}
                            </p>
                            <span className="right-content">看地图<span className="yo-ico">&#xf07f;</span></span>
                        </li>
                    </Touchable>
                    
                    <Touchable touchClass="touchable-highlight">
                        <li className="double-line">
                            <p>
                                {this.props.openTime}
                            </p>
                            <p>
                                {
                                    this.props.facilities.map((src, i) => <img src={src} key={i} />)
                                }
                            </p>
                            <span className="right-content">酒店设施<span className="yo-ico">&#xf07f;</span></span>
                        </li>
                    </Touchable>
                </ul>
            </div>
        )
    }
}

export default Detail;