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
const r=createBrowserRouter(createRoutesFromElements(
     
      <Route path='/' element={<App/>}>
       
    <Route path='contact' element={<Contact/>}/>
    <Route path='about' element={<About/>}/>
    <Route path='features' element={<Features/>}/>
    <Route path='signup' element={<Signup/>}/>
<Route path='terms' element={<Terms/>}/>

    
    <Route path='login' element={<Login/>}/>
    
    
  </Route>
      
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
