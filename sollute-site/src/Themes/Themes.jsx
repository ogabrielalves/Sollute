import { createTheme } from '@mui/material/styles';

const theme = createTheme({
    palette: {
        primary: {
            main: '#000000',
        },
        secondary: {
            light: '#0066ff',
            main: '#0044ff',
            contrastText: '#ffcc00',
        },
        contrastThreshold: 3,
        tonalOffset: 0.2,
    },
    contrastThreshold: 3,
    tonalOffset: 0.2,

    components: {
        MuiButton: {
            styleOverrides: {
                contained: {
                    backgroundColor: '#3E00FF',
                    transition: '0.4s',
                    cursor: 'pointer',
                    '&:hover': {
                        backgroundColor: 'white',
                        color: '#3E00FF'
                    }
                },
                outlined: {
                    backgroundColor: 'transparent',
                    color: '#3E00FF',
                    border: '2px solid #3E00FF',
                    transition: '0.4s',
                    cursor: 'pointer',
                    '&:hover': {
                        backgroundColor: '#3E00FF',
                        color: 'white',
                        border: '2px solid #3E00FF'
                    }
                }
            }
        }
    }
})

export default theme;