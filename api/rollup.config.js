import resolve from '@rollup/plugin-node-resolve';
import commonjs from '@rollup/plugin-commonjs';
import typescript from '@rollup/plugin-typescript'
import { terser } from 'rollup-plugin-terser';
import replace from "rollup-plugin-replace";

// `npm run generated` -> `production` is true
// `npm run dev` -> `production` is false
const production = !process.env.ROLLUP_WATCH;

export default {
    input: 'app/pages/index.tsx',
    output: {
        file: './src/main/resources/static/generated/bundle.js',
        format: 'iife',
        sourcemap: true
    },
    plugins: [
        resolve(),
        commonjs(),
        typescript(),
        replace({
            'process.env.NODE_ENV': production ? JSON.stringify( 'production' ) : JSON.stringify( 'development' )
        }),
        production && terser() // minify, but only in production
    ]
};
