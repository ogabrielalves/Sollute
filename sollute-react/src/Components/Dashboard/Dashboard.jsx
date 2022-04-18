import * as React from 'react';
import { styled } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import MuiDrawer from '@mui/material/Drawer';
import Box from '@mui/material/Box';
import MuiAppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import MenuPopUp from '../MenuPopUp/MenuPopUp';
import LogoSollute from '../../Assets/Image/Logo2-modelo.svg'

import PointOfSaleIcon from '@mui/icons-material/PointOfSale';
import LocalOfferIcon from '@mui/icons-material/LocalOffer';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import EqualizerIcon from '@mui/icons-material/Equalizer';
import PersonIcon from '@mui/icons-material/Person';
import InventoryIcon from '@mui/icons-material/Inventory';
import GroupIcon from '@mui/icons-material/Group';
import HomeIcon from '@mui/icons-material/Home';
import SupervisedUserCircleIcon from '@mui/icons-material/SupervisedUserCircle';

import { Link } from 'react-router-dom'

import useMediaQuery from '@mui/material/useMediaQuery';

class Dashboard extends React.Component {
    render() {
        return (
            <DashboardContent {...this.props}>{this.props.children}</DashboardContent>
        );
    }
}

function DashboardContent(props) {

    const matches = useMediaQuery('(min-width: 900px)')

    const [open, setOpen] = React.useState(!matches);


    const toggleDrawer = () => {
        setOpen(!open);
    };

    const menuDefault = [{
        title: 'Início',
        link: '/dashboard',
        icon: <HomeIcon />
    },
    {
        title: 'Caixa',
        link: '',
        icon: <PointOfSaleIcon />
    },
    {
        title: 'Produtos',
        link: '/dashboard/product',
        icon: <LocalOfferIcon />
    },
    {
        title: 'Relatório de vendas',
        link: '',
        icon: <EqualizerIcon />,
    },
    {
        title: 'Estoque',
        link: '',
        icon: <InventoryIcon />
    },
    {
        title: 'Funcionário',
        link: '',
        icon: <PersonIcon />
    },
    {
        title: 'Fornecedores',
        link: '',
        icon: <SupervisedUserCircleIcon />
    }, {
        title: 'Clientes',
        link: '',
        icon: <GroupIcon />
    }];

    const drawerWidth = 240;

    const Drawer = styled(MuiDrawer, { shouldForwardProp: (prop) => prop !== 'open' })(
        ({ theme, open }) => ({
            '& .MuiDrawer-paper': {
                position: 'relative',
                whiteSpace: 'nowrap',
                width: drawerWidth,
                transition: theme.transitions.create('width', {
                    easing: theme.transitions.easing.sharp,
                    duration: theme.transitions.duration.enteringScreen,
                }),
                boxSizing: 'border-box',
                ...(!open && {
                    overflowX: 'hidden',
                    transition: theme.transitions.create('width', {
                        easing: theme.transitions.easing.sharp,
                        duration: theme.transitions.duration.leavingScreen,
                    }),
                    width: theme.spacing(7),
                    [theme.breakpoints.up('sm')]: {
                        width: theme.spacing(9),
                    },
                }),
            },
        }),
    );

    React.useEffect(() => {
        if (matches) {
            setOpen(true)
        } else {
            setOpen(false)
        }
    }, [matches])

    return (

        <Box sx={{ display: 'flex' }}>
            <CssBaseline />

            <Drawer variant="permanent" open={open}>
                <Toolbar
                    sx={{
                        display: 'flex',
                        alignItems: 'center',
                        justifyContent: 'flex-end',
                        px: [1],
                    }}
                >
                    <Link to={'/'}><img src={LogoSollute} width={'70%'} className="logo_style" alt="" /></Link>
                    <IconButton onClick={toggleDrawer}>
                        <ChevronLeftIcon />
                    </IconButton>
                </Toolbar>

                <Divider />

                <List key="menu_pt_1" >
                    {menuDefault.map((text, index) => {
                        if (text.children !== undefined) {
                            return (
                                <MenuPopUp route={text} key={index}></MenuPopUp>
                            )
                        }
                        return (
                            <Link key={text.title} to={text.link} style={{ textDecoration: 'none', color: '#000000' }}>
                                <ListItem button>
                                    <ListItemIcon>
                                        {text.icon}
                                    </ListItemIcon>
                                    <ListItemText primary={text.title} />
                                </ListItem>
                            </Link>
                        )
                    })}
                </List>
                <Divider />
            </Drawer>

            <Box
                component="main"
                sx={{
                    backgroundColor: (theme) =>
                        theme.palette.mode === 'light'
                            ? theme.palette.grey[100]
                            : theme.palette.grey[900],
                    flexGrow: 1,
                    height: '100vh',
                    overflow: 'auto',
                }}
            >
                <Toolbar />
                <Container maxWidth="false" sx={{ mt: 4, mb: 4 }} >

                    <Grid container spacing={2}>
                        <Grid item xs={12} md={12} lg={12}>
                            <Paper
                                sx={{
                                    p: 2,
                                    display: 'flex',
                                    flexDirection: 'column',
                                    autoHeight: true,
                                }}
                            >
                                {props.children}
                            </Paper>
                        </Grid>
                    </Grid>


                </Container>
            </Box>
        </Box>
    );
}


export default Dashboard;