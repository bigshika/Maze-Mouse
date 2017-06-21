import React, { Component } from 'react';
import './App.css';

class Cell extends Component {
  constructor(props) {
    super(props);
    this.state = {up: true, right: true, down: true, left: true};
  }

  makeActive() {
    this.setState({isActive: true});
  }

  makeInactive() {
    this.setState({isActive: false});
  }

  getWalls() {
    var dashLength = 0;
    var spaceLength = 0;
    var dashArrayProperty = "";
    if (this.state.up) {
      dashLength = 100;
    } else {
      spaceLength = 100;     
      dashArrayProperty = "0 ";
    }

    if (this.state.right) {
      if (spaceLength > 0) {
        dashArrayProperty += spaceLength + " ";
        spaceLength = 0;
      }
      dashLength += 100;
    } else {
      if (dashLength > 0) {
        dashArrayProperty += dashLength + " ";
        dashLength = 0;
      }
      spaceLength += 100;
    }

    if (this.state.down) {
      if (spaceLength > 0) {
        dashArrayProperty += spaceLength + " ";
        spaceLength = 0;
      }
      dashLength += 100;
    } else {
      if (dashLength > 0) {
        dashArrayProperty += dashLength + " ";
        dashLength = 0;
      }
      spaceLength += 100;
    }

     if (this.state.left) {
      if (spaceLength > 0) {
        dashArrayProperty += spaceLength + " 100";
        spaceLength = 0;
      } else {
        dashLength += 100;        
        dashArrayProperty += dashLength;
      }
    } else {
      if (dashLength > 0) {
        dashArrayProperty += dashLength + " 100";
      } else {
        spaceLength += 100;
        dashArrayProperty += spaceLength;
      }
    }   

    return dashArrayProperty; 
  }

  getIdNames() {
    if (this.props.rowNumber === 0 && this.props.colNumber === 0) {
      return "Entrance";
    } else if (this.props.rowNumber === 9 && this.props.colNumber === 9) {
      return "Exit";
    }
    return "";
  }

  render() {
    let idName = this.getIdNames();
    let dasharray = this.getWalls();
    return (
      <svg xmlns="http://www.w3.org/2000/svg"  width="100" height="100">
        <rect id={idName} className="Cell" x="0" y="0" width="100" height="100" style={{strokeDasharray:dasharray}}/>
      </svg>  
      );
    }
  }

export default Cell;
