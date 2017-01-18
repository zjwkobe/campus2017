import 'babel-polyfill';
import React, {Componet} from 'react';
import ReactDOM from 'react-dom';
import {Router, Route, IndexRoute, Link} from '$router';
import HomePage from './home';
import yoHistory from '../../common/history';

const Root = () => (
    <Router history={yoHistory}>
        <Route path="/">
            <IndexRoute component={HomePage}/>
        </Route>
    </Router>
);

ReactDOM.render(<Root />, document.getElementById('root'));