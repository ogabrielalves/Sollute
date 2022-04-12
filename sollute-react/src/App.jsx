import React from "react";
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

import Home from './Pages/Home/Home';
import Register from './Pages/Register/Register';
import Login from './Pages/Login/Login';
import Contact from './Pages/Contact/Contact';
import Plans from './Pages/Plans/Plans';

import Product from './Pages/Dashboard/Product/Product'
import HomeDashboard from './Pages/Dashboard/Home/Home'

function App() {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<Home/>} />
        <Route exact path="/register" element={<Register/>} />
        <Route exact path="/login" element={<Login/>} />
        <Route exact path="/contact" element={<Contact/>} />
        <Route exact path="/plans" element={<Plans/>} />

        <Route exact path="/dashboard/product" element={<Product/>} />
        <Route exact path="/dashboard" element={<HomeDashboard/>} />
      </Routes>
    </Router>
  );
}

export default App;