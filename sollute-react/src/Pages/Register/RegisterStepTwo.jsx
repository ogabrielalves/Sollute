import React, { Component } from 'react';
import RegisterPage from '../../Components/RegisterPage/RegisterPage';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Grid from '@mui/material/Grid';
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';

class RegisterStepTwo extends Component {
    state = {}
    render() {
        return (
            <RegisterPage colorOne={'#26E07F'}>
                <Grid container spacing={1} >
                    <Grid item xs={12} mb={8}>
                        <h1>Insira as informações da sua empresa</h1>
                        <p style={{ color: '#8E8E8E', width: '39%' }}>Coloque mais informações sobre sua empresa, para saber de como devemos chama-la.</p>
                    </Grid>
                    <Grid item xs={12} md={8} mb={2} >
                        <TextField fullWidth id="outlined-basic" label="Nome Fantasia" variant="outlined" />
                    </Grid>
                    <Grid item xs={12} md={8} mb={2}>
                        <TextField fullWidth id="outlined-basic" label="Razão Social" variant="outlined" />
                    </Grid>
                    <Grid item xs={12} md={8}>
                        <TextField fullWidth id="outlined-basic" label="CNPJ" variant="outlined" />
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

export default RegisterStepTwo;