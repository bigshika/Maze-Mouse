import React, { Component } from 'react';
import './App.css';
import Cell from './Cell.js';

class Grid extends Component {

  render() {
    let row = [];
    for (let i = 0; i < 10; i++) {
      row.push(<Cell rowNumber={this.props.rowNumber} colNumber={i} key={this.props.rowNumber + " " + i}/>)
    } 

    return (
      <div className="GridRow">
        {row}
      </div>
    );
  }
}

export default Grid;
