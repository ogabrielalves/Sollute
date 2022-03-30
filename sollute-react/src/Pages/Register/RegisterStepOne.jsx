import React, { Component } from 'react';
import RegisterPage from '../../Components/RegisterPage/RegisterPage';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Grid from '@mui/material/Grid';
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';

class RegisterSetpOne extends Component {
    state = {}
    render() {
        return (
            <RegisterPage>
                <Grid container spacing={1} >
                    <Grid item xs={12} mb={8}>
                        <h1>Insira seus dados</h1>
                        <p style={{ color: '#8E8E8E', width: '39%' }}>Coloque seu e-mail e senha, suas informações estarão seguras conosco.</p>
                    </Grid>
                    <Grid item xs={12} md={8} mb={2} >
                        <TextField fullWidth id="outlined-basic" label="E-mail" variant="outlined" type={'email'} />
                    </Grid>
                    <Grid item xs={12} md={8}>
                        <TextField fullWidth id="outlined-basic" label="Senha" variant="outlined" type={'password'} />
                    </Grid>
                    <Grid item md={4}></Grid>
                    <Grid item md={4}></Grid>
                    <Grid item xs={12} md={4}> 
                        <Button fullWidth variant="contained" endIcon={<ArrowForwardIosIcon />}>Próximo passo</Button>
                    </Grid>
                </Grid>
            </RegisterPage>
        );
    }
}

export default RegisterSetpOne;