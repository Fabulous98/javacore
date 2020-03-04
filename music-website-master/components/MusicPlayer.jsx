'use-strict';

import React from 'react';
import { Grid, Row, Col } from 'react-bootstrap';

const BandCamp = {
    runaway: 'https://bandcamp.com/EmbeddedPlayer/track=1747464792/size=large/bgcol=333333/linkcol=0f91ff/minimal=true/transparent=true/',
    storm: 'https://bandcamp.com/EmbeddedPlayer/track=2448303870/size=large/bgcol=333333/linkcol=0f91ff/minimal=true/transparent=true/'
};

const style = {
    border: '0',
    width: '100%',
    height: '700px'
};

export default class MusicPlayer extends React.Component {

    render() {

        return (

            <Grid id='music'>
                <Row>
                    <Col xs={12} sm={12}>
                        <h1>Recordings</h1>
                    </Col>
                    <Col xs={12} sm={6}>
                        <h3>First Date <i>- released April 21, 2018</i></h3>
                        <iframe style={style} src={BandCamp.runaway} seamless>
                            <a href='https://frad.bandcamp.com/track/first-date'>First Date</a>
                        </iframe>
                    </Col>
                    <Col xs={12} sm={6}>
                        <h3>Luv Letters <i>- released April 04, 2019</i></h3>
                        <iframe style={style} src={BandCamp.storm} seamless>
                            <a href='https://frad.bandcamp.com/track/luv-letters'>Luv Letters</a>
                        </iframe>
                    </Col>
                </Row>
            </Grid>
        );
    }
}
