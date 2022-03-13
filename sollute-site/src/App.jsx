import React, { Component } from 'react';
import Grid from '@mui/material/Grid';
import Button from '@mui/material/Button';
import logo from './Assets/Image/Logo2-modelo.svg';
import './App.css';

const styleMenu = {
  display: 'flex',
  listStyleType: 'none',
  justifyContent: 'space-between',
  textAlign: 'center',
  fontWeight: 500
}

const styleBorder = {
  borderRadius: '10px'
}

class App extends Component {
  state = {}
  render() {
    return (
      <Grid backgroundColor={'#DAD2F1'} height={'100vh'}>
        <Grid container p={4}>
          <Grid item xs={12} md={2}>
            <img src={logo} alt="" />
          </Grid>
          <Grid item xs={12} md={7} p={4}>
            <ul className='menu' style={styleMenu}>
              <li style={{ borderBottom: '4px solid #3E00FF', width: '10%' }}>Home</li>
              <li>Sobre</li>
              <li>Preços</li>
              <li>Contato</li>
            </ul>
          </Grid>
          <Grid item xs={12} md={1} padding={'25px 0'} ml={16}>
            <Button sx={styleBorder} fullWidth variant="contained">Cadastre-se</Button>
          </Grid>
          <Grid item xs={12} md={1} padding={'25px 0'} ml={2}>
            <Button sx={styleBorder} fullWidth variant="outlined">Login</Button>
          </Grid>
        </Grid>
      </Grid>
    );
  }
}

export default App;