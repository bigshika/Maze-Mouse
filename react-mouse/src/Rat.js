import React, { Component } from 'react';
import './App.css';
import rat from './PolyesterUnderwearRat.png';

class Rat extends Component {
  constructor(props) {
    super(props);
    this.state = {row: 0, col: 0, facingRight: true};
    extendObservable(this, {
        start: this.state.row,
        current: Date.now(),
        get elapsedTime() {
            return (this.current - this.start) + "seconds"
        },
        tick: action(function() {
              this.current = Date.now()
        })
    })

    this.handleKeyPress = this.handleKeyPress.bind(this);
  }
  componentDidMount() {
    window.addEventListener('keypress', this.handleKeyPress);
  }
  componentWillUnmount() {
    window.removeEventListener('keypress', this.handleKeyPress);
  }

  handleKeyPress(e) {
    switch(e.key) {
      case 'w':
        this.moveUp();
        break;
      case 's':
        this.moveDown();
        break;
      case 'a':
        this.moveLeft();
        break;
      case 'd':
        this.moveRight();
        break;
      default:
        break;
    }
  }

  moveUp() {
    if (this.state.row > 0) {
      this.setState({row: this.state.row - 1});
    }
  }

  moveDown() {
    if (this.state.row < 9) {
      this.setState({row: this.state.row + 1});
    }
  }

  moveLeft() {
    if (this.state.col > 0) {
      this.setState({col: this.state.col - 1});
      this.setState({facingRight: false});
    }
  }

  moveRight() {
    if (this.state.col < 9) {
      this.setState({col: this.state.col + 1});
      this.setState({facingRight: true});
    }
  }

  render() {
    return (
      <div className="Rat" style={{top:this.state.row * 100, left:this.state.col * 100}}>
        <img id={this.state.facingRight ? "facingRight" : ""} className="Rat" src={rat} alt="Fig. 1 The underpant worn by the rat"/>
      </div>
      );
    }
  }

export default Rat;
