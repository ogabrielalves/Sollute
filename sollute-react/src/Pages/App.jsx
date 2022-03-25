import React, { Component } from 'react';
import NavBar from '../Components/NavBar/NavBar';
import './App.css';
import ImagemCentral from '../Assets/Image/Manage money-rafiki 1.svg';
import Button from '@mui/material/Button';

class App extends Component {
  state = {}
  render() {
    return (
      <div>
        <NavBar />
        <div className='container'>
          <div className='containerText'>
            <h1>Estoque Certo</h1>
            <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book</p>
            <Button style={{marginTop: 15, fontSize: '1.2rem'}} variant="contained">Cadastre-se</Button>
          </div>
          <div className="containerImage">
            <img className='' src={ImagemCentral} alt="" />
          </div>
        </div>
      </div>
    );
  }
}

export default App;