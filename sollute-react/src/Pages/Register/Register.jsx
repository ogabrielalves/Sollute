import React, { Component } from 'react';
import RegisterStepOne from '../Register/RegisterStepOne';
import RegisterStepTwo from '../Register/RegisterStepTwo';
import RegisterStepThree from '../Register/RegisterStepThree';

class Register extends Component {
    state = {}
    render() {
        
        return (
            <div>
                <RegisterStepOne />
                {/* <RegisterStepTwo /> */}
                {/* <RegisterStepThree/> */}
            </div>
        );
    }
}

export default Register;