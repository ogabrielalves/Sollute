import React, { Component } from 'react';
import RegisterPage from '../../Components/RegisterPage/RegisterPage';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Grid from '@mui/material/Grid';
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';
import CheckIcon from '@mui/icons-material/Check';

function Register() {

    const [one, setOne] = React.useState(true);
    const [two, setTwo] = React.useState(false);
    const [three, setThree] = React.useState(false);

    return (
        <div>
            {/* Step One */}
            {one &&
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
                            <Button
                                fullWidth
                                variant="contained"
                                endIcon={<ArrowForwardIosIcon />}
                                onClick={() => {
                                    setTwo(true)
                                    setOne(false)
                                }}>
                                Próximo passo
                            </Button>
                        </Grid>
                    </Grid>
                </RegisterPage>
            }

            {/* Step Two */}
            {two &&
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
                            <Button
                                fullWidth
                                variant="contained"
                                endIcon={<ArrowForwardIosIcon />}
                                onClick={() => {
                                    setTwo(false)
                                    setThree(true)
                                }}>
                                Próximo passo
                            </Button>
                        </Grid>
                    </Grid>
                </RegisterPage>
            }

            {/* Step Three */}
            {three &&
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
            }
        </div>
    );

}

export default Register;