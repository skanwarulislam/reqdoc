FROM node:alpine
WORKDIR /app
## Copy code
COPY package.json .
COPY package-lock.json .
COPY ./src  ./src
COPY ./tsconfig.json .
COPY ./public ./public
RUN ls -al
RUN npm install
CMD ["npm", "run", "start"]