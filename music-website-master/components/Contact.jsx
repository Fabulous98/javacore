'use-strict';

import React from 'react';

export default class Contact extends React.Component {

    render(){

        return (
            
                <section id="contact">
                
                <div className="container">
                    <div className="row">
                    <div className="col-lg-9 col-md-9 col-sm-9">
                    
                        <form id="contactform" action="assets/php/submit.php" method="post">
                        <div className="row">
                            <div className="col-lg-5 col-md-5 col-sm-5">
                            <h5>Your Name:</h5>
                            <input type="text" id="name" />
                            </div>
                            <div className="col-lg-5 col-md-5 col-sm-5">
                            <h5>Your Email:</h5>
                            <input type="text" name="email" id="email" />
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-lg-12 col-md-12 col-sm-12">
                            <h5>Your Message:</h5>
                            <textarea name="message" id="message" defaultValue={""} />
                            </div>
                        </div>
                        <button id="submit1" type="submit">Submit</button>
                        </form>
                        <br></br>
                        <div id="valid-issue" style={{display: 'none'}}> Please Provide Valid Information</div>   
                    </div>
                    </div>
                </div>
                </section>
           
      );
    }
  }