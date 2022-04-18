import React, { useState } from 'react';
import Dashboard from '../../../Components/Dashboard/Dashboard';
import ProductService from '../../../Services/Product/ProductService';

import DeleteIcon from '@mui/icons-material/Delete';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';

import { Grid, TextField, Button } from '@mui/material';

function DeleteProduct() {
    const center = {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center'
    }

    const [id, setId] = useState('');

    async function deleteProduto(id) {
        const service = new ProductService()
        await service.deleteProdutos(id)
    }
    deleteProduto(id)
    return (
        <Dashboard>
            <form action="">
                <Grid container spacing={2} sx={center}>
                    <Grid item xs={12} marginBottom={2} style={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
                        <DeleteIcon style={{ marginRight: '20px' }} />
                        <h1>Excluir Produto</h1>
                    </Grid>
                    <Grid item xs={12} md={8} marginBottom={2}>
                        <TextField fullWidth id="outlined-basic" label="ID do Produto" variant="outlined"
                            onChange={(evt) => setId(evt.target.value)} />
                    </Grid>
                    <Grid item xs={12}></Grid>
                    <Grid item xs={12} md={4}>
                        <Button
                            onClick={deleteProduto(id)}
                            fullWidth
                            variant="contained"
                            startIcon={<DeleteIcon />}
                        >
                            Excluir
                        </Button>
                    </Grid>
                    <Grid item xs={12} md={4}>
                        <Button
                            fullWidth
                            variant="outlined"
                            onClick={() => window.location.href = "/dashboard/product"}
                            startIcon={<ArrowBackIcon />}
                        >
                            Voltar
                        </Button>
                    </Grid>
                </Grid>
            </form>
        </Dashboard>
    );
}

export default DeleteProduct;