import axios from 'axios';

const urlBase = 'http://localhost:8080/empresas';
const headers = {
  'Content-Type': 'application/json'
};
class ProductService {
  constructor() {
    this.state = {
      data: []
    };
  }

  async getProdutos() {
    return await axios.get(`${urlBase}/listar-produtos-vestuario/55756157000133`, {
      headers: headers
    })
      .then(res => res.data)
      .catch((err) => {
        console.error(`request failed ${err}`);
      });
  }

  async postProdutos(obj) {
    return await axios.post(`${urlBase}/criar-produto-vestuario/55756157000133`, 
      obj
    )
      .then(res => res.data)
      .catch((err) => {
        console.error(`request failed ${err}`);
      });
  }

  async deleteProdutos({ codigo }) {
    const { data } = await axios.delete(`${urlBase}/deletar-produto/${codigo}`);
    return data;
  }
}


export default ProductService;