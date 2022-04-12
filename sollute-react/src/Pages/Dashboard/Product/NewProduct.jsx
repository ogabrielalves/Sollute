import { React, useState } from 'react';
import Dashboard from '../../../Components/Dashboard/Dashboard';
import { TextField, Grid, Button } from '@mui/material';
import ProductService from '../../../Services/Product/ProductService'

import LocalOfferIcon from '@mui/icons-material/LocalOffer';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';

// Style
const styleGridButton = {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    marginTop: '20px'
}


function NewProduct() {
    const [nome, setNome] = useState('');
    const [marca, setMarca] = useState('');
    const [categoria, setCategoria] = useState('');
    const [peso, setPeso] = useState('');
    const [estoque, setEstoque] = useState('');
    const [preco, setPreco] = useState('');

    async function postProduto() {
        const service = new ProductService()
        await service.postProdutos({
            "cnpj": "55756157000133",
            "nome": nome,
            "preco": preco,
            "qtdEstoque": estoque,
            "qtdVendidos": 0,
            "valorVendidos": 0,
            "marca": marca,
            "peso": peso,
            "categoria": categoria,
            "tipoVestuario": "Camisa",
            "tamanho": "M"
        })
    }


    return (
        <Dashboard>
            <Grid container spacing={3}>
                <Grid item xs={12} style={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
                    <LocalOfferIcon style={{ marginRight: '20px' }} />
                    <h1>Novo Produto</h1>
                </Grid>
                <Grid item xs={12}>
                    <h2>Dados Gerais</h2>
                </Grid>
                <Grid item xs={12} md={4}>
                    <TextField fullWidth id="outlined-basic" label="Nome do produto" variant="outlined" onChange={(evt) => setNome(evt.target.value)} />
                </Grid>
                <Grid item xs={12} md={4}>
                    <TextField fullWidth id="outlined-basic" label="Código do produto" variant="outlined" />
                </Grid>
                <Grid item xs={12} md={4}>
                    <TextField fullWidth id="outlined-basic" label="Marca do produto" variant="outlined" onChange={(evt) => setMarca(evt.target.value)} />
                </Grid>
                <Grid item xs={12} md={4}>
                    <TextField fullWidth id="outlined-basic" label="Categoria" variant="outlined" onChange={(evt) => setCategoria(evt.target.value)} />
                </Grid>
                <Grid item xs={12} md={4}>
                    <TextField fullWidth id="outlined-basic" label="Peso" variant="outlined" onChange={(evt) => setPeso(evt.target.value)} />
                </Grid>
                <Grid item xs={12} md={4}>
                    <TextField fullWidth id="outlined-basic" label="Validade" variant="outlined" />
                </Grid>

                <Grid item xs={12}>
                    <h2>Estoque</h2>
                </Grid>
                <Grid item xs={12} md={4}>
                    <TextField fullWidth id="outlined-basic" label="Estoque Inicial" variant="outlined" onChange={(evt) => setEstoque(evt.target.value)} />
                </Grid>
                <Grid item xs={12} md={4}>
                    <TextField fullWidth id="outlined-basic" label="Estoque Mínimo" variant="outlined" />
                </Grid>
                <Grid item xs={12} md={4}>
                    <TextField fullWidth id="outlined-basic" label="Estoque Máximo" variant="outlined" />
                </Grid>

                <Grid item xs={12}>
                    <h2>Preços</h2>
                </Grid>
                <Grid item xs={12} md={4}>
                    <TextField fullWidth id="outlined-basic" label="Preço de compra" variant="outlined" />
                </Grid>
                <Grid item xs={12} md={4}>
                    <TextField fullWidth id="outlined-basic" label="Preço de venda" variant="outlined" onChange={(evt) => setPreco(evt.target.value)} />
                </Grid>


                <Grid container spacing={3} sx={styleGridButton}>
                    <Grid item xs={12} md={3}>
                        <Button
                            fullWidth
                            variant="contained"
                            startIcon={<CheckCircleIcon />}
                            onClick={() => postProduto()}
                        >
                            Finalizar
                        </Button>
                    </Grid>
                    <Grid item xs={12} md={3}>
                        <Button
                            fullWidth
                            variant="outlined"
                            startIcon={<ArrowBackIcon />}
                            onClick={() => window.location.href = "/dashboard/product"}
                        >
                            Voltar
                        </Button>
                    </Grid>
                </Grid>
            </Grid>
        </Dashboard>
    );
}

export default NewProduct;