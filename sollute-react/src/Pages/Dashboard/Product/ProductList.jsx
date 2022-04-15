import React, { useState, useEffect } from 'react';
import { DataGrid } from '@mui/x-data-grid';
import ProductService from '../../../Services/Product/ProductService';
const service = new ProductService()

function ProductList(props) {

    const [pageSize, setPageSize] = useState(10)
    const [page, setPage] = useState(0)

    const [items, setItems] = useState([])

    useEffect(() => {
        getData()
        async function getData() {

            const apiResponse = await service.getProdutos()
            console.log(apiResponse)

            setItems(apiResponse)
        }
    }, [])

    return (
        <DataGrid
            sortable={true}
            filter={true}
            density="compact"
            autoWidth={true}
            rowHeight={70}
            columns={columns}
            getRowId={(row) => row.codProduto}
            rows={items}           
            page={page}
            pageSize={pageSize}
            onPageSizeChange={(newPageSize) => setPageSize(newPageSize)}
            onPageChange={(newPage) => setPage(newPage)}

            rowsPerPageOptions={[10, 20, 30]}
            autoHeight={true} >
        </DataGrid>
    )
}


const columns = [
    {
        field: "codigo",
        headerName: "Código do produto",
        width: 290
    },
    {
        field: "nome",
        headerName: "Nome",
        width: 290
    },
    {
        field: "precoVenda",
        headerName: "Preço",
        width: 200
    },
    {
        field: "estoqueInicial",
        headerName: "Quantidade em estoque",
        width: 200
    },
    {
        field: "marca",
        headerName: "Marca",
        width: 220
    },
    {
        field: "peso",
        headerName: "Peso",
        width: 120
    },
    {
        field: "categoria",
        headerName: "Categoria",
        width: 150
    },
    {
        field: "tipoVestuario",
        headerName: "Tipo de vestuario",
        width: 220
    }

];

export default ProductList;