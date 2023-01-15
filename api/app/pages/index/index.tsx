import * as React from "react";
import {useEffect, useState} from "react";
import {AppBar, Button, Divider, ImageListItem, Stack} from "@mui/material";
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Avatar from '@mui/material/Avatar';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Unstable_Grid2';
import {GoogleLogin, GoogleOAuthProvider} from "@react-oauth/google";
import {createRoot} from "react-dom/client";
import {IndexDispatcher} from "./dispatcher";
import {House, HouseService} from "../../api/houses/HouseService";
import UserStore from "../../store/user/UserStore";

const pages = ['Add your home'];

interface AppBarProps {
    onLogin: (token: string) => void
}

interface AppBarMenuProps {
    onClose: () => void
    onLogin: (token: string) => void
    anchor: null | HTMLElement
}

const KeyAppBarMenu: React.FC<AppBarMenuProps> = (
    {
        onClose,
        onLogin,
        anchor
    }
) => {
    const [user, setUser] = React.useState<User | null>()

    React.useEffect(() => {
        const id = UserStore.observeUser((user) => {
            setUser(user)
        })

        return UserStore.removeObserver(id)
    }, [])

    return (
        <Menu
            sx={{mt: '45px'}}
            id="menu-appbar"
            anchorEl={anchor}
            anchorOrigin={{
                vertical: 'top',
                horizontal: 'right',
            }}
            keepMounted
            transformOrigin={{
                vertical: 'top',
                horizontal: 'right',
            }}
            PaperProps={{sx: {width: '240px'}}}
            open={Boolean(anchor)}
            onClose={onClose}
        >
            {
                user == null
                    ? <GoogleOAuthProvider
                        clientId="895061217340-vckj2gbhjiekphkg5lg3f6eaf1e5c0tv.apps.googleusercontent.com">

                        <GoogleLogin
                            onSuccess={credentialResponse => {
                                console.log(credentialResponse)
                                onLogin(credentialResponse.credential)
                            }}
                            onError={() => {
                                console.log('Login Failed');
                            }}
                        />
                    </GoogleOAuthProvider>
                    : null
            }

            <MenuItem key={"Messages"} onClick={onClose}
                      sx={{height: '40px', pl: '20px'}}>
                <Typography textAlign="center" fontSize={"14px"}
                            fontWeight={500}>{"Messages"}</Typography>
            </MenuItem>

            <MenuItem key={"Trips"} onClick={onClose}
                      sx={{height: '40px', pl: '20px'}}>
                <Typography textAlign="center" fontSize={"14px"}
                            fontWeight={500}>{"Trips"}</Typography>
            </MenuItem>

            <MenuItem key={"Wishlist"} onClick={onClose}
                      sx={{height: '40px', pl: '20px'}}>
                <Typography textAlign="center" fontSize={"14px"}
                            fontWeight={500}>{"Wishlist"}</Typography>
            </MenuItem>

            <Divider/>

            <MenuItem key={"AddYourHome"} onClick={onClose}
                      sx={{height: '40px', pl: '20px'}}>
                <Typography textAlign="center" fontSize={"14px"}
                            fontWeight={400}>{"Add your home"}</Typography>
            </MenuItem>

            <MenuItem key={"Account"} onClick={onClose}
                      sx={{height: '40px', pl: '20px'}}>
                <Typography textAlign="center" fontSize={"14px"}
                            fontWeight={400}>{"Account"}</Typography>
            </MenuItem>

            <Divider/>

            <MenuItem key={"Help"} onClick={onClose}
                      sx={{height: '40px', pl: '20px'}}>
                <Typography textAlign="center" fontSize={"14px"}
                            fontWeight={400}>{"Help"}</Typography>
            </MenuItem>

            <MenuItem key={"LogOut"} onClick={onClose}
                      sx={{height: '40px', pl: '20px'}}>
                <Typography textAlign="center" fontSize={"14px"} fontWeight={500}
                            sx={{color: 'red'}}>{"Log out"}</Typography>
            </MenuItem>
        </Menu>
    )
}

const KitAppBar: React.FC<AppBarProps> = (
    { onLogin }
) => {
    const [anchorElNav, setAnchorElNav] = React.useState<null | HTMLElement>(null);
    const [anchorElUser, setAnchorElUser] = React.useState<null | HTMLElement>(null);

    const handleOpenNavMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElNav(event.currentTarget);
    };
    const handleOpenUserMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElUser(event.currentTarget);
    };

    const handleCloseNavMenu = () => {
        setAnchorElNav(null);
    };

    const handleCloseUserMenu = () => {
        setAnchorElUser(null);
    };

    return (<AppBar position="sticky" elevation={0} sx={{backgroundColor: "white"}}>
            <Stack>
                <Container maxWidth="xl">
                    <Toolbar disableGutters>
                        <Box sx={{flexGrow: 1, display: {xs: 'flex', md: 'none'}}}>
                            <IconButton
                                size="large"
                                aria-label="account of current user"
                                aria-controls="menu-appbar"
                                aria-haspopup="true"
                                onClick={handleOpenNavMenu}
                                color="inherit"
                            >
                                <MenuIcon sx={{color: "black"}}/>
                            </IconButton>
                            <Menu
                                id="menu-appbar"
                                anchorEl={anchorElNav}
                                anchorOrigin={{
                                    vertical: 'bottom',
                                    horizontal: 'left',
                                }}
                                keepMounted
                                transformOrigin={{
                                    vertical: 'top',
                                    horizontal: 'left',
                                }}
                                open={Boolean(anchorElNav)}
                                onClose={handleCloseNavMenu}
                                sx={{
                                    display: {xs: 'block', md: 'none'},
                                }}
                            >
                                {pages.map((page) => (
                                    <MenuItem key={page} onClick={handleCloseNavMenu}>
                                        <Typography textAlign="center">{page}</Typography>
                                    </MenuItem>
                                ))}
                            </Menu>
                        </Box>

                        <Typography
                            noWrap
                            component="a"
                            href=""
                            fontSize={'18px'}
                            sx={{
                                mr: 2,
                                display: {xs: 'flex', md: 'none'},
                                flexGrow: 0,
                                fontWeight: 600,
                                letterSpacing: '.2rem',
                                color: 'crimson',
                                textDecoration: 'none',
                            }}
                        >
                            Kitten
                        </Typography>

                        <Typography
                            noWrap
                            component="a"
                            href=""
                            fontSize={'18px'}
                            sx={{
                                mr: 2,
                                display: {xs: 'none', md: 'flex'},
                                flexGrow: 0,
                                fontWeight: 600,
                                letterSpacing: '.1rem',
                                color: 'deeppink',
                                textDecoration: 'none',
                            }}
                        >
                            Kitten
                        </Typography>

                        <Box sx={{flexGrow: 1, display: {xs: 'none', md: 'flex'}}}/>

                        <Box sx={{flexGrow: 0, mr: 2, display: {xs: 'none', md: 'flex'}}}>
                            {pages.map((page) => (
                                <Button
                                    key={page}
                                    onClick={handleCloseNavMenu}
                                    sx={{
                                        my: 2,
                                        color: 'black',
                                        display: 'block',
                                        textTransform: 'none',
                                        fontWeight: 520
                                    }}
                                >
                                    {page}
                                </Button>
                            ))}
                        </Box>

                        <Box sx={{flexGrow: 0}}>
                            <Tooltip title="Open settings">
                                <IconButton onClick={handleOpenUserMenu} sx={{p: 0}}>
                                    <Avatar
                                        alt="UserLogo"
                                        src="https://lh3.googleusercontent.com/a/AEdFTp4jm2UYMkYqRWMDRJd9pwA9mGmq4nONuzZXpx0w=s96-c"
                                    />

                                    <KeyAppBarMenu
                                        onLogin={onLogin}
                                        onClose={handleCloseUserMenu}
                                        anchor={anchorElUser}/>
                                </IconButton>
                            </Tooltip>
                        </Box>
                    </Toolbar>
                </Container>
                <Divider/>
            </Stack>
        </AppBar>
    );
}

const service = new HouseService()

export default function Body() {
    const [houses, setHouses] = useState<House[]>([])

    useEffect(() => {
        const token = service.getHouses()
            .subscribe(
                (result) => setHouses(result),
                (error) => console.log(error)
            )

        return token.cancel
    }, [])

    return (
        <Box sx={{ flexGrow: 1, pl: '82px', pr: '82px', pt: '32px', pb: '32px' }}>
            <Grid container spacing={{ xs: 2, md: 3 }} columns={{ xs: 4, sm: 8, md: 12, lg: 16 }}>
                {houses.map((house, index) => (
                    <Grid xs={2} sm={4} md={4} key={index}>
                        <Stack>
                            <ImageListItem key={index} sx={{ backgroundColor: "transparent", pb: '8px' }}>
                                <img
                                    src={house.photo}
                                    alt={"Error"}
                                    loading="lazy"
                                    style={{ borderRadius: '12px', aspectRatio: 1 }}
                                />
                            </ImageListItem>

                            <Typography fontSize={"15px"} fontWeight={600}>{house.address.city}, {house.address.country}</Typography>
                            <Typography fontSize={"14px"} fontWeight={400} sx={{ color: 'gray' }}>1,021 kilometers away</Typography>
                            <Typography fontSize={"14px"} fontWeight={400} sx={{ color: 'gray', pb: '6px' }}>Mar 12 – 17</Typography>
                            <div style={{display:"flex"}}>
                                <Typography fontSize={"15px"} fontWeight={600}><b>${house.price}</b></Typography>
                                <Typography fontSize={"15px"} fontWeight={400} sx={{ color: 'gray' }}>&nbsp;night</Typography>
                            </div>
                        </Stack>
                    </Grid>
                ))}
            </Grid>
        </Box>
    );
}

function MainPage() {
    const dispatcher = new IndexDispatcher()
    dispatcher.getUser()

    return (
        <Stack>
            <KitAppBar
                onLogin = {(token: string) => {
                    dispatcher.login(token)
                }}
            />
            <Body/>
        </Stack>
    )
}

createRoot(document.getElementById('root'))
    .render(<MainPage/>);