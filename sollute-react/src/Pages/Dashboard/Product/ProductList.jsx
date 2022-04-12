import React, {useState, useEffect } from 'react';
import { DataGrid } from '@mui/x-data-grid';
import ProductService from '../../../Services/Product/ProductService';
const service = new ProductService()

function ProductList(){

    const [pageSize, setPageSize] = useState(10)
    const [page, setPage] = useState(0)

    const [items, setItems] = useState([])
    const [rowCount, setRowCount] = useState(0)

    useEffect(()=>{
        getData()
        async function getData(){

            const apiResponse = await service.getProdutos()          
        }
    }, [])

    return(
        <DataGrid
                sortable={true}
                filter={true}
                density="compact"
                autoWidth={true}
                rowHeight={70}
                columns={columns}

                rows={items}
                paginationMode="server"
                page={page}
                pageSize={pageSize}
                onPageSizeChange={(newPageSize) => setPageSize(newPageSize)}
                onPageChange={(newPage)=>setPage(newPage)}
    	        rowCount={rowCount}

                rowsPerPageOptions={[10, 20, 30]}
                autoHeight={true} >
        </DataGrid>
    )
}


const columns = [
    {
        field: "nome",
        headerName: "Nome",
        width: 290
    },
    {
        field: "preco",
        headerName: "Preço",
        width: 200
    },
    {
        field: "qtdEstoque",
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
        width:220
    }
    
];

export default ProductList;