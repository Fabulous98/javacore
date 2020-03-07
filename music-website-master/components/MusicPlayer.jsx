'use-strict';

import React from 'react';
import { Grid, Row, Col } from 'react-bootstrap';

const BandCamp = {
    runaway: 'https://bandcamp.com/EmbeddedPlayer/album=603546913/size=large/bgcol=333333/linkcol=0f91ff/tracklist=false/track=2504498281/transparent=true/',
    storm: 'https://bandcamp.com/EmbeddedPlayer/album=603546913/size=large/bgcol=333333/linkcol=0f91ff/tracklist=false/track=2915641562/transparent=true/'
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
                        <h3>Sadness DearestDearest <i>- released March 7, 202020</i></h3>
                        <iframe style={style} src={BandCamp.runaway} seamless>
                            <a href='https://bmusics.bandcamp.com/track/sadness-dearest'>First Date</a>
                        </iframe>
                    </Col>
                    <Col xs={12} sm={6}>
                        <h3>Sadness Violin <i>- released March 7, 202020</i></h3>
                        <iframe style={style} src={BandCamp.storm} seamless>
                            <a href='https://bmusics.bandcamp.com/track/sadness-violin'>Luv Letters</a>
                        </iframe>
                    </Col>
                </Row>
            </Grid>
        );
    }
}
