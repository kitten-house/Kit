import resolve from '@rollup/plugin-node-resolve';
import commonjs from '@rollup/plugin-commonjs';
import typescript from '@rollup/plugin-typescript'
import json from '@rollup/plugin-json'
import { terser } from 'rollup-plugin-terser';
import replace from "rollup-plugin-replace";
import nodePolyfills from 'rollup-plugin-polyfill-node';

// `npm run generated` -> `production` is true
// `npm run dev` -> `production` is false
const production = false // !process.env.ROLLUP_WATCH;

export default {
    input: 'app/pages/index/index.tsx',
    output: {
        file: './src/main/resources/static/generated/bundle.js',
        format: 'iife',
        name: 'bundle.js',
        sourcemap: true
    },
    plugins: [
        resolve(),
        commonjs(),
        typescript(),
        json(),
        nodePolyfills(),
        replace({
            'process.env.NODE_ENV': production ? JSON.stringify( 'development' ) : JSON.stringify( 'development' )
        }),
        production && terser() // minify, but only in production
    ]
};
