import React  from 'react';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Grid from '@mui/material/Grid';
import useMediaQuery from '@mui/material/useMediaQuery';

import RegisterPage from '../../Components/RegisterPage/RegisterPage';
import PopOver from '../../Components/PopOver/PopOver';

import CheckIcon from '@mui/icons-material/Check';
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';

function Register() {
    const matches = useMediaQuery('(max-width:1325px)');

    function screenFit(page) {
        var styleButtonBack = 0;
        switch (page) {
            case 1:
                if (matches) {
                    styleButtonBack = {
                        width: '30%',
                        marginRight: '5px'
                    }
                }
                else {
                    styleButtonBack = {
                        width: '30%',
                        marginRight: '25px'
                    }
                }
                break;

            case 2:
                if (matches) {
                    styleButtonBack = {
                        width: '35.5%'                       
                    }
                }
                else {
                    styleButtonBack = {
                        width: '34%'                       
                    }
                }
                break;

            default:
                break;
        }
        return styleButtonBack
    }

    // Usado para mudar a página
    const [one, setOne] = React.useState(true);
    const [two, setTwo] = React.useState(false);
    const [three, setThree] = React.useState(false);

    return (
        <div>
            {/* Step One */}
            {one &&
                <RegisterPage>
                    <Grid container spacing={1} >
                        <Grid item xs={12} mb={5}>
                            <h1>Insira seus dados</h1>
                            <p style={{ color: '#8E8E8E', width: '60%' }}>Coloque seu e-mail e senha, suas informações estarão seguras conosco.</p>
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
                        <Grid item xs={12} mb={5}>
                            <h1>Insira as informações da sua empresa</h1>
                            <p style={{ color: '#8E8E8E', width: '60%' }}>Coloque mais informações sobre sua empresa, para saber de como devemos chama-la.</p>
                        </Grid>
                        <Grid item xs={12} md={8} mb={2} style={{ display: 'flex', alignItems: 'center' }}>
                            <TextField fullWidth id="outlined-basic" label="Nome Fantasia" variant="outlined" />
                            <PopOver>O nome fantasia é o nome da sua marca.</PopOver>
                        </Grid>
                        <Grid item xs={12} md={8} mb={2} style={{ display: 'flex', alignItems: 'center' }}>
                            <TextField fullWidth id="outlined-basic" label="Razão Social" variant="outlined" />
                            <PopOver>A razão social é o nome completo da Pessoa Física seguido do CPF do titular do MEI.</PopOver>
                        </Grid>
                        <Grid item xs={12} md={8} style={{ display: 'flex', alignItems: 'center', marginBottom: 6 }}>
                            <TextField fullWidth id="outlined-basic" label="CNPJ" variant="outlined" />
                            <PopOver>O número estará no seu CCMEI, o Certificado da Condição do Microempreendedor Individual.</PopOver>
                        </Grid>
                        <Grid item md={4}></Grid>
                        <Grid item xs={12} md={12} style={{ display: 'flex' }}>
                            <Button
                                style={screenFit(1)}
                                variant="outlined"
                                startIcon={<ArrowBackIosIcon />}
                                onClick={() => {
                                    setOne(true)
                                    setTwo(false)
                                }}>
                                Voltar
                            </Button>
                            <Button
                                style={{ width: '30%' }}
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
                        <Grid item xs={12} mb={5}>
                            <h1>Insira suas informações de endereço</h1>
                            <p style={{ color: '#8E8E8E', width: '60%' }}>Digite seu endereço para sabermos onde fica localizado sua empresa, apenas para fins comerciais.</p>
                        </Grid>
                        <Grid item xs={12} md={8} mb={2} >
                            <TextField fullWidth id="outlined-basic" label="CEP" variant="outlined" />
                        </Grid>
                        <Grid item xs={4}></Grid>
                        <Grid item xs={12} md={1} mb={2}>
                            <TextField fullWidth id="outlined-basic" label="UF" variant="outlined" />
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
                        <Grid item xs={12} md={12} style={{ display: 'flex' }}>
                            <Button
                                style={screenFit(1)}
                                variant="outlined"
                                startIcon={<ArrowBackIosIcon />}
                                onClick={() => {
                                    setTwo(true)
                                    setThree(false)
                                }}>
                                Voltar
                            </Button>
                            <Button
                                style={screenFit(2)}
                                variant="contained"
                                endIcon={<CheckIcon />}
                                onClick={() => {
                                    setTwo(false)
                                    setThree(true)
                                }}>
                                Finalizar
                            </Button>
                        </Grid>
                    </Grid>
                </RegisterPage>
            }
        </div>
    );

}

export default Register;