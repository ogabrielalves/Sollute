import React, { Component } from "react";
import { Grid } from "@mui/material";
import ImagemCadastro from '../../Assets/Image/img-login.svg';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';



//Styles
const StylesLogin = {
    display: 'Flex',
    mt: 6, //margin top
    marginLeft: 5,
    alignItems: 'center',
    pl: 3, //padding left
    flexDirection: 'column'
}

const leftBar = {
    backgroundColor: 'white',
    height: '100vh',
    display: 'flex',
    justifyContent: 'center',
    flexDirection: 'column',
    padding: '0 30px'
}


function LoginPage(props) {

    return (
        <Grid container>

            <Grid item xs={5} style={leftBar}>

                <Grid mb={2}>
                    <TextField fullWidth size="large" id="outlined-basic" label="E-mail" variant="outlined" type={'email'} />
                </Grid>
                <Grid mb={2}>
                    <TextField fullWidth id="outlined-basic" label="Password" variant="outlined" type={'password'} />
                </Grid>
                <Grid>
                    <Button fullWidth
                        variant="contained">
                        Logar
                    </Button>
                </Grid>

            </Grid>
            <Grid item xs={7} style={{backgroundColor: "#DAD2F1"}}>
                <img width={400} src={ImagemCadastro} alt="img-cadastro"  />
            </Grid>
        </Grid >
    );
}


export default LoginPage;