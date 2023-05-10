/** @type {import('tailwindcss').Config} */
const colors = require('tailwindcss/colors');
module.exports = {
    content: [
        "../resources/templates/*.html",
        "../resources/templates/**/*.html"
    ],
    theme: {
        extend: {
            colors: {
                colors: {
                    transparent: 'transparent',
                    current: 'currentColor',
                    black: colors.black,
                    white: colors.white,
                    gray: colors.gray,
                    emerald: colors.emerald,
                    indigo: colors.indigo,
                    yellow: colors.yellow,
                },
            },
        },
    },
    plugins: [
        require('@tailwindcss/forms'),
    ],
}