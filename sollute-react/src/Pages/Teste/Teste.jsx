import * as React from 'react';
import useMediaQuery from '@mui/material/useMediaQuery';

export default function SimpleMediaQuery() {
    const matches = useMediaQuery('(max-width:1325px)');

    if (matches) {
        return <span>Bateu 1325</span>;
    }
    else {
        return <span>Bateu 1920</span>;
    }

}