import React from 'react';
import Grid from '@mui/material/Grid';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import LogoSollute from '../../Assets/Image/Logo2-modelo.svg';

// Styles
const styleChecks = {
    display: 'flex',
    mt: 6,
    alignItems: 'center',
    pl: 3
}

function RegisterPage(props) {
    return (
        <Grid container>
            <Grid item xs={3} style={{ backgroundColor: 'white', height: '100vh', color: '#939393' }}>
                <Grid style={{ margin: '10px 0' }}>
                    <img src={LogoSollute} alt="" width={150} style={{ marginLeft: 10 }} />
                </Grid>
                <Grid sx={styleChecks}>
                    <CheckCircleIcon sx={{color: props.colorOne, marginRight: 1, fontSize: '1.5em'}} />
                    <h2>DADOS DE ENTRADA</h2>
                </Grid>
                <Grid sx={styleChecks}>
                    <CheckCircleIcon sx={{color: props.colorTwo, marginRight: 1, fontSize: '1.5em'}} />
                    <h2>DADOS DA EMPRESA</h2>
                </Grid>
                <Grid sx={styleChecks}>
                    <CheckCircleIcon sx={{color: props.colorThree, marginRight: 1, fontSize: '1.5em'}} />
                    <h2>DADOS DE ENDEREÇO</h2>
                </Grid>
            </Grid>
            <Grid item xs={9} style={{ backgroundColor: '#FAFAFA', height: '100vh' }}>
                <Grid position={'absolute'} right={50} top={50}>
                    <p>Está  com problemas? <b style={{ color: '#3E00FF' }}>Fale Conosco</b></p>
                </Grid>
                <Grid style={{height: '80vh', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
                    <Grid style={{ display: 'flex', alignItems: 'center', marginLeft: '20vw', justifyContent: 'center' }}>
                        {props.children}
                    </Grid>
                </Grid>
            </Grid>
        </Grid>
    );
}

export default RegisterPage;