require('dotenv').config()
const mongoose = require('mongoose');
mongoose.set('strictQuery', false);

exports.getConnections = () => mongoose.connect(process.env.DATABASE_URL, {
    useNewUrlParser: true,
    useUnifiedTopology: true
})
    .then(() => console.log('Connexion à MongoDB réussie !'))
    .catch((e) => console.log('Connexion à MongoDB échouée !', e));
