import React from "react";
import { Grid } from "@mui/material";
import ImagemCadastro from '../../Assets/Image/img-login.svg';
import LogoSollute from '../../Assets/Image/Logo2-modelo.svg';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';

//Styles
const leftBar = {
    backgroundColor: 'white',
    height: '100vh',
    display: 'flex',
    justifyContent: 'center',
    flexDirection: 'column',
    padding: '0 30px'
}

const rightBar = {
    backgroundColor: "#DAD2F1", 
    display: 'flex', 
    flexDirection: 'column', 
    alignItems: 'center', 
    justifyContent: 'center'
}

const botaoCadastrese = {
    width: 400, 
    fontWeight: 'bold', 
    marginTop: 10, 
    backgroundColor: '#511281', 
    borderColor: '#511281'
}


function Login() {

    return (
        <Grid container>

            <Grid item xs={5} style={leftBar}>

                <Grid mb={12} style={{ marginLeft: 140 }}>
                    <img src={LogoSollute} alt="Logo Sollute" onClick={() => window.location.href = "/"} style={{ cursor: 'pointer' }} />
                    <Grid mt={6}>
                        <span style={{ textTransform: 'uppercase', fontWeight: 'bold' }}>Acesse seu Perfil da Sollute</span>
                    </Grid>
                </Grid>
                <Grid mb={2}>
                    <TextField fullWidth size="large" id="outlined-basic" label="E-mail" variant="outlined" type={'email'} />
                </Grid>
                <Grid mb={2}>
                    <TextField fullWidth id="outlined-basic" label="Senha" variant="outlined" type={'password'} />
                </Grid>
                <Grid>
                    <Button fullWidth
                        variant="contained">
                        Logar
                    </Button>
                    <Grid mt={2}>
                        <span style={{ fontWeight: 'bold', fontSize: 15 }}>Esqueci Minha Senha</span>
                    </Grid>
                </Grid>

            </Grid>

            <Grid item xs={7} style={rightBar}>
                
                <img width={400} src={ImagemCadastro} alt="img-cadastro" />

                <span style={{ fontWeight: 'bold', fontSize: 18}}>Ainda não possui uma conta?</span>


                <Button fullWidth variant="contained" style={botaoCadastrese} onClick={() => window.location.href = "/register"}>Cadstre-se</Button>
            
            </Grid>
        </Grid >
    );
}


export default Login;