import React, {Component} from 'react';
import Touchable from '$yo-component/touchable';
import './index.scss';

const tagMap = {
    'group': '团',
    'night': '夜销',
    'return': '可返星券',
    'most-reliable': '可靠',
    'cheapest': '最便宜',
    'fast-confirm': '确认'
}

class PriceItem extends Component {
    constructor(props) {
        super(props);
        this.state = {
            expanded: this.props.config.expanded
        }
    }
    _compare() {
        this.setState({expanded: !this.state.expanded})
        this.props.hide()
    }
    render() {
        return (
            <div className={this.props.config.type === 'initial' ? "price-item initial-item" : "price-item"}>
                <div className="price-left">
                    <h4>{this.props.config.title}</h4>
                    {this.props.config.remark ? <p className="remark">{this.props.config.remark}</p> : null}
                    <p className="additional-tags">
                        {this.props.config.additionalTags ? this.props.config.additionalTags.map((value, i) => {
                            switch(value) {
                                case 'group':
                                    return <span className="tag-group" key={i}>{tagMap['group']}</span>
                                    break;
                                case 'night':
                                    return <span className="tag-group" key={i}>{tagMap['night']}</span>
                                    break;
                                case 'return':
                                    return <span className="tag-return" key={i}>{tagMap['return']}</span>
                                    break;
                                case 'cheapest':
                                    return <span className="tag-cheapest" key={i}>{tagMap['cheapest']}</span>
                                    break;
                                case 'most-reliable':
                                    return  <span className="tag-most" key={i}>{tagMap['most-reliable']}</span>
                                    break;
                                case 'fast-confirm':
                                    return  <span className="tag-most" key={i}>{tagMap['night']}</span>
                                    break;
                            }
                        }) : null}
                    </p>
                </div>
                <div className="price-right">
                    <div className="price-info">
                        <p className={this.props.config.priceRemark ? "price-amount" :"price-amount only-price"}>{this.props.config.price}</p>
                        <div className="remark">
                            {this.props.config.priceRemark ? (
                                this.props.config.priceRemark.map((config, i) => {
                                    if (i === 0) {
                                        if (config.type === 'RMB') {
                                            return (<div key={i}>付款￥{config.amount}</div>)
                                        }
                                    }
                                    if (i === 1) {
                                        if (config.type === 'RMB') {
                                            return (<div key={i}>可返:￥{config.amount}</div>)
                                        }
                                        if (config.type === 'ticket') {
                                            return (<div key={i}>可返:{config.amount}星券</div>)
                                        }
                                    }
                                })
                            ) : null}
                        </div>
                    </div>
                    <div className={this.props.config.type === 'initial' ? "price-action price-action-compare" : "price-action price-action-order"}>
                        <Touchable touchClass="touchable-hightlight">
                            {
                                (this.props.config.type === 'initial') ? 
                                (<Touchable touchClass="touchable-hightlight"  onTap={this._compare.bind(this)}><div className="compare-btn">比价<span className={this.state.expanded ? 'yo-ico compare-expanded' : 'yo-ico compare-collapse'}>&#xf2ad;</span></div></Touchable>) : 
                                (<div className="order-btn">
                                    <p className="order-title">订</p>
                                    <p className="order-remark">{this.props.config.payMethod}</p>
                                </div>)
                            }
                        </Touchable>
                    </div>
                </div>
            </div>
        )
    }
}

export default PriceItem;