import React, { Component } from 'react';
import Grid from '@mui/material/Grid';
import TextField from '@mui/material/TextField';

import ContactPage from '../../Components/ContactPage/ContactPage';

class Contact extends Component {
    state = {}
    render() {
        return (
            <ContactPage>
                <Grid container style={{flexDirection: 'column'}}>
                    <Grid item>
                    <h1>Converse Conosco</h1>
                    <p style={{ width: '60%', color: '#8E8E8E' }}>
                        Você pode entrar em contato conosco a qualquer momento via <b style={{color: '#3E00FF'}}>Sollute@gmail.com</b>
                    </p>
                    </Grid>

                    <Grid item xs={12} md={3}>
                    <TextField fullWidth id="outlined-basic" label="Nome" variant="outlined" />
                    </Grid>
                    <Grid item xs={12} md={3}>
                    <TextField fullWidth id="outlined-basic" label="E-mail" variant="outlined" />
                    </Grid>
                </Grid>
            </ContactPage>
        );
    }
}

export default Contact;