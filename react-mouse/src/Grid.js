import React, { Component } from 'react';
import './App.css';
import GridRow from './GridRow.js';

class Grid extends Component {

  render() {
    let grid = [];
    for (let i = 0; i < 10; i++) {
      grid.push(<GridRow rowNumber={i} key={i}/>)
    }

    return (
      <div className="Grid">
        {grid}
      </div>
    );
  }
}

export default Grid;
