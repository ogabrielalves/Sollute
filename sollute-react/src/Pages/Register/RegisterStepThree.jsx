import React, { Component } from 'react';
import RegisterPage from '../../Components/RegisterPage/RegisterPage';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Grid from '@mui/material/Grid';
import CheckIcon from '@mui/icons-material/Check';

class RegisterStepThree extends Component {
    state = {}
    render() {
        return (
            <RegisterPage colorOne={'#26E07F'} colorTwo={'#26E07F'}>
                <Grid container spacing={1} >
                    <Grid item xs={12} mb={8}>
                        <h1>Insira suas informações de endereço</h1>
                        <p style={{ color: '#8E8E8E', width: '39%' }}>Digite seu endereço para sabermos onde fica localizado sua empresa, apenas para fins comerciais.</p>
                    </Grid>
                    <Grid item xs={12} md={8} mb={2} >
                        <TextField fullWidth id="outlined-basic" label="CEP" variant="outlined" />
                    </Grid>
                    <Grid item xs={4}></Grid>
                    <Grid item xs={12} md={1} mb={2}>
                        <TextField fullWidth id="outlined-basic" label="Estado" variant="outlined" />
                    </Grid>
                    <Grid item xs={12} md={7} mb={2}>
                        <TextField fullWidth id="outlined-basic" label="Cidade" variant="outlined" />
                    </Grid>
                    <Grid item xs={12} md={8} mb={2}>
                        <TextField fullWidth id="outlined-basic" label="Logradouro" variant="outlined" />
                    </Grid>
                    <Grid item xs={12} md={8}>
                        <TextField fullWidth id="outlined-basic" label="Ponto de refêrencia" variant="outlined" />
                    </Grid>
                    <Grid item md={4}></Grid>
                    <Grid item md={4}></Grid>
                    <Grid item xs={12} md={4}>
                        <Button fullWidth variant="contained" endIcon={<CheckIcon />}>Finalizar</Button>
                    </Grid>
                </Grid>
            </RegisterPage>
        );
    }
}

export default RegisterStepThree;