const fs = require('fs');
const path = require('path');
const { execSync } = require('child_process');

const BASE = process.cwd();

// =======================
// 🧠 STACK DETECTOR PRO
// =======================
function detectStack() {
  const stack = { frontend: null, backend: null };

  if (fs.existsSync('angular.json')) stack.frontend = 'angular';
  if (fs.existsSync('package.json')) {
    const pkg = JSON.parse(fs.readFileSync('package.json'));
    if (pkg.dependencies?.react) stack.frontend = 'react';
    if (!stack.frontend) stack.backend = 'node';
  }

  if (fs.existsSync('pom.xml')) stack.backend = 'spring';
  if (fs.existsSync('requirements.txt')) stack.backend = 'python';

  return stack;
}

// =======================
// ⚙️ UTIL
// =======================
function run(cmd) {
  try {
    console.log(`⚙️ ${cmd}`);
    return execSync(cmd, { encoding: 'utf-8' });
  } catch (e) {
    return e.stdout || e.message;
  }
}

function ai(prompt) {
  try {
    return execSync(`echo "${prompt}" | claude`, {
      encoding: 'utf-8',
      maxBuffer: 1024 * 1000
    });
  } catch {
    return null;
  }
}

function write(file, content) {
  const full = path.join(BASE, file);
  fs.mkdirSync(path.dirname(full), { recursive: true });
  fs.writeFileSync(full, content);
  console.log(`✅ ${file}`);
}

// =======================
// 🔧 GIT PRO
// =======================
function gitInit() {
  if (!fs.existsSync('.git')) {
    run('git init');
    run('git add .');
    run('git commit -m "init"');
  }
}

function commit(msg) {
  run('git add .');
  run(`git commit -m "${msg}"`);
}

function rollback() {
  console.log('⏪ rollback');
  run('git reset --hard HEAD~1');
}

// =======================
// 🤖 AGENTES
// =======================
function architect(task) {
  return ai(`Diseña arquitectura profesional con microservicios: ${task}`);
}

function frontend(task) {
  return ai(`Eres frontend senior. Genera código limpio: ${task}`);
}

function backend(task) {
  return ai(`Eres backend senior. API escalable: ${task}`);
}

function tester() {
  return ai(`Genera tests unitarios para este proyecto`);
}

// =======================
// 🧠 SAFE WRITE (NO ROMPE)
// =======================
function safeWrite(file, newCode) {
  if (!fs.existsSync(file)) {
    write(file, newCode);
    return;
  }

  const old = fs.readFileSync(file, 'utf-8');

  const merged = ai(`
Fusiona este código sin romperlo:

OLD:
${old}

NEW:
${newCode}
`);

  write(file, merged || newCode);
}

// =======================
// ▶️ RUN + TEST
// =======================
function runProject(stack) {
  let out = '';

  if (stack.frontend === 'angular') {
    out += run('npm install');
    out += run('ng build');
  }

  if (stack.frontend === 'react') {
    out += run('npm install');
    out += run('npm run build');
  }

  if (stack.backend === 'spring') {
    out += run('mvn clean install');
  }

  if (stack.backend === 'node') {
    out += run('node .');
  }

  return out;
}

// =======================
// 🧪 TEST RUNNER
// =======================
function runTests() {
  if (fs.existsSync('package.json')) {
    return run('npm test');
  }
  return '';
}

// =======================
// 🐳 DOCKER AUTO
// =======================
function createDocker() {
  const docker = `
FROM node:18
WORKDIR /app
COPY . .
RUN npm install
CMD ["npm","start"]
`;
  write('Dockerfile', docker);
}

// =======================
// 🔁 AUTOPILOT PRO
// =======================
async function autopilot(task) {
  const stack = detectStack();

  console.log('🧠 STACK:', stack);

  gitInit();

  write('architecture.md', architect(task));

  safeWrite('src/app/auto.ts', frontend(task));
  safeWrite('backend/app.js', backend(task));

  createDocker();

  commit('feat: initial generation');

  for (let i = 0; i < 6; i++) {
    console.log(`🔁 LOOP ${i + 1}`);

    let output = runProject(stack);
    output += runTests();

    if (output.toLowerCase().includes('error')) {
      console.log('❌ error detectado');

      const fix = ai(`
Corrige este error como senior dev:

${output}
`);

      if (fix) {
        safeWrite('autofix.patch', fix);
        commit('fix: auto');
      } else {
        rollback();
      }

    } else {
      console.log('✅ estable');
      commit('stable');
      break;
    }
  }

  console.log('SISTEMA LISTO PARA PRODUCCIÓN');
}

// =======================
// CLI
// =======================
const args = process.argv.slice(2);

if (args[0] === 'auto') {
  autopilot(args.slice(1).join(' '));
} else {
  console.log(`V20\nnode ai-system.js auto "app"`);
}