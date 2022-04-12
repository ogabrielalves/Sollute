import React, {useState, useEffect } from 'react';
import { DataGrid } from '@mui/x-data-grid';
import ProductService from '../../../Services/Product/ProductService';
const service = new ProductService()

function ProductList(props){

    const [pageSize, setPageSize] = useState(10)
    const [page, setPage] = useState(0)

    const [items, setItems] = useState([])
    const [rowCount, setRowCount] = useState(0)

    useEffect(()=>{
        getData()
        async function getData(){

            const filters = props.filters;

            const apiResponse = await service.getPOSPagination(page+1, pageSize, filters)
            console.log(apiResponse)
            setRowCount(apiResponse.totalRecords)
            setItems(apiResponse.items)
        }
    }, [pageSize, page, props.filters])

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

const situacao = [{ key: 1, value: "Não Liberado Para Instalação" },
{ key: 2, value: "Liberado Para Instalação" },
{ key: 3, value: "Instalado" },
{ key: 4, value: "Inativo" },
{ key: 5, value: "Transacionando" }]

const columns = [
    {
        field: "identifier",
        headerName: "Identificador",
        width: 120
    },
    {
        field: "automationName",
        headerName: "Nome Fantasia",
        width: 200
    },
    {
        field: "a1",
        headerName: "ID do Sistema Externo",
        width: 200
    },
    {
        field: "description",
        headerName: "Descrição",
        width: 220
    },
    {
        field: "automationCompanyName",
        headerName: "Razão Social",
        width: 280
    },   
    {
        field: "a45",
        headerName: "CPF/CNPJ",
        width: 120
    },
    {
        field: "pointOfSaleType",
        headerName: "Tipo de PDC",
        width: 120,
        valueGetter: (v) => {
            if(v.row.pointOfSaleType === null) {
                return ""
            }
            return v.row.pointOfSaleType.description
        }
    },
    {
        field: "a6",
        headerName: "Aplicação Instalada",
        width: 160
    },
    {
        field: "applicationVersionInstalled",
        headerName: "Versão Instalada",
        width: 200,
        valueGetter: (v) => {
            if(v.row.applicationVersionInstalled === null) {
                return ""
            }
            return v.row.applicationVersionInstalled.version
        }
    },
    {
        field: "applicationVersionForInstallation",
        headerName: "Versão Para Instalação",
        width: 200,
        valueGetter: (v) => {
            if(v.row.applicationVersionForInstallation === null) {
                return ""
            }
            return v.row.applicationVersionForInstallation.version
        }
    },
    {
        field: "status",
        headerName: "Situação",
        width: 180,
        valueGetter: (v) => situacao.find(e => e.key === v.value).value
    },
    {
        field: 'lastCommunicationAt',
        headerName: 'Última Comunicação',
        width: 160,
        valueGetter: (v) => {
            const x = new Date(v.value)
            return [x.getDate(), x.getMonth() + 1, x.getFullYear()].map(e => e < 10 ? '0' + e : e).join('/')
                + " " + [x.getHours(), x.getMinutes()].map(e => e < 10 ? '0' + e : e).join(':')
        }
    },
    {
        field: 'lastTransactionAt',
        headerName: 'Última Transação Financeira',
        width: 160,
        valueGetter: (v) => {
            const x = new Date(v.value)
            return [x.getDate(), x.getMonth() + 1, x.getFullYear()].map(e => e < 10 ? '0' + e : e).join('/')
                + " " + [x.getHours(), x.getMinutes()].map(e => e < 10 ? '0' + e : e).join(':')
        }
    }
];

export default ProductList;