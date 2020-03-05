'use-strict';

import React from 'react';
import { Grid, Row, Col} from 'react-bootstrap';

export default class Projects extends React.Component {

    render() {

        return (

            <Grid id='projects'>
                <h1> My Musical Projects </h1>
                <Row className='show-grid'>
                    <Col xs={12}>
                        <h2>Frad Music</h2>
                        <p>Discover my music!</p>
                        <p><a href='https://frad.bandcamp.com/'>My bandcamp!</a></p>
                        <p>Get contact for more information:</p>
                    </Col>
                </Row>
            </Grid>
        );
    }
}
