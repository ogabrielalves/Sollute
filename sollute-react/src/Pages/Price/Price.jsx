import React from "react";
import { Grid, Button } from "@mui/material";
import CardPrice from "../../Components/CardPrice/CardPrice";
import ImgCard1 from "../../Assets/Image/Typing-pana 1.svg";
import ImgCard2 from "../../Assets/Image/Typing-cuate 2.svg";
import ImgCard3 from "../../Assets/Image/Co-workers-cuate 3.svg";
import LogoSollute from '../../Assets/Image/Logo2-modelo.svg';




//Styles
const fundo = {
    backgroundColor: 'white',
    height: '100vh',
    width: '100vw',
    textAlign: 'center'
}

const cards = {
    display: 'flex',
    justifyContent: 'space-between',
    padding: '0 160px'
}

const botao = {
    display: 'flex', 
    alignItems: 'center', 
    justifyContent: 'center'
}


function Preco() {
    return (

        <Grid container style={fundo} >
            <Grid>
                <img src={LogoSollute} />
            </Grid>

            <Grid style={{ width: '72vw', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>

                <h1>ESCOLHA SEU PLANO</h1>

            </Grid>

            <Grid container style={cards}>

                <Grid>

                    <CardPrice
                        image={ImgCard1}
                        title="Pao de batata"
                        color="#511AFF"

                        variant='outlined' />
                </Grid>

                <Grid>
                    <CardPrice
                        image={ImgCard2}
                        title="Pao de batata"
                        color="#784DFF"

                        variant='contained' />
                </Grid>

                <Grid>
                    <CardPrice
                        image={ImgCard3}
                        title="Pao de batata"
                        color="#9F80FF"
                        variant='outlined' />
                </Grid>
            </Grid>

                <Grid ml={72}>
                    <Button style={botao} variant="contained">
                        VOLTAR AO INICIO →
                    </Button>
                </Grid>

        </Grid >



    );
}

export default Preco;



