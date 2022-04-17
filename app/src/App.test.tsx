import React from 'react';
import {render, screen} from '@testing-library/react';
import App from './App';

test('renders learn react link', () => {
  render(<App/>);
  const requiredDocElement = screen.getByText(/Required Docs/i);
  const currencyElement = screen.getByText(/Currency/);
  expect(requiredDocElement).toBeInTheDocument();
  expect(currencyElement).toBeInTheDocument();
});
