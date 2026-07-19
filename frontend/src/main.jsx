import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import 'bootstrap/dist/css/bootstrap.min.css';
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import {createBrowserRouter,Route,createRoutesFromElements, RouterProvider} from 'react-router-dom';
import App from './App';
import Contact from './pages/Contact';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import ThemeProvider from './components/ThemeProvider';
import "bootstrap-icons/font/bootstrap-icons.css";
import './index.css'
import About from './pages/About';
import Features from './pages/Features';
import Login from './components/Login';
import Signup from './components/Signup';
import Terms from './components/Terms';
import { Invalid } from './pages/Invalid';
import { Expired } from './pages/Expired';
import { AlreadyVerified } from './pages/AlreadyVerified';
import { Verified } from './pages/Verified';
import ResendVerificationEmail from './pages/ResendVerificationEmail';
import ForgotPassword from './pages/ForgotPassword';
import Userdashboard from './components/Userdashboard';
import Admindashboard from './components/Admindashboard';
const r=createBrowserRouter(createRoutesFromElements(
     <>
      <Route path='/' element={<App/>}>
       
    <Route path='contact' element={<Contact/>}/>
    <Route path='about' element={<About/>}/>
    <Route path='features' element={<Features/>}/>
    <Route path='signup' element={<Signup/>}/>
<Route path='terms' element={<Terms/>}/>

</Route>
<Route path='login' element={<Login/>}/>
  <Route path='invalid' element={<Invalid/>}/>
  <Route path='verified' element={<Verified/>}/>
  <Route path='alreadyverified' element={<AlreadyVerified/>}/>
  <Route path='expired' element={<Expired/>}/>
  <Route path='resend-verification' element={<ResendVerificationEmail/>}/>
  <Route path='forgot-password' element={<ForgotPassword/>} />
  <Route path='user' element={<Userdashboard/>}/>
  <Route path='admin' element={<Admindashboard/>} />
  </>
)
     
)
createRoot(document.getElementById('root'))
.render(
  <StrictMode>
    <ThemeProvider>
<RouterProvider router={r}/>
</ThemeProvider>

  </StrictMode>
);
