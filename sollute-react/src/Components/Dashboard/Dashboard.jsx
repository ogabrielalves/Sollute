import React  from 'react';
import { Grid } from '@mui/material';

const leftBar = {
    backgroundColor: '#784DFF',
    height: '100vh',
}

const rightBar = {
    backgroundColor: 'white',
    height: '100vh',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    padding: '0 30px'
}

function Dashboard(props) {
    return (
        <Grid container >
            <Grid item xs={3} sx={leftBar}></Grid>
            <Grid item xs={9} sx={rightBar}>
                {props.children}
            </Grid>
        </Grid >
    );
}

export default Dashboard;