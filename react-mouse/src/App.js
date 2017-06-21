import React, { Component } from 'react';
import logo from './PolyesterUnderwearRat.png';
import './App.css';
import Grid from './Grid.js';
import Rat from './Rat.js';

class App extends Component {

   constructor(props) {
    super(props);
    this.state = {row: 0, col: 0, cell: null};
  }

  setActiveCell(cell) {
    this.setState({cell: cell})
  }

  setActiveCoordinates(row, col) {
    this.setState({row: row, col: col});
  }

  render() {
    return (
      <div className="App"  onKeyPress={this.handleKeyPress}>
        <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h2>Welcome to Maze</h2>
        </div>
        <p className="App-intro">
          Use WASD to get to the end!
        </p>
        <div className="Game">
          <Grid activeRow={this.state.row} activeCol={this.state.col}/>
          <Rat activeCell={this.state.cell}/>
        </div>
      </div>
    );
  }
}

export default App;
