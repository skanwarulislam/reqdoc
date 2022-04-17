import "./App.css";
// importing components from react-router-dom package
import {BrowserRouter as Router, Route, Routes,} from "react-router-dom";

import Home from "./components/Home";
import Currency from "./components/Currency";
import Country from "./components/Country";

function App() {
  return (
      <>
        {/* This is the alias of BrowserRouter i.e. Router */}
        <Router>
          <Routes>
            <Route path="/" element={<Home/>}/>
            <Route path="/country" element={<Country/>}/>
            <Route path="/currency" element={<Currency/>}/>
          </Routes>
        </Router>
      </>
  );
}

export default App;